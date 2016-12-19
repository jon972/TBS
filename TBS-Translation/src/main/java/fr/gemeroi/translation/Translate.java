package fr.gemeroi.translation;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import fr.gemeroi.common.utils.Language;
import fr.gemeroi.persistence.bean.Subtitle;
import fr.gemeroi.persistence.bean.User;
import fr.gemeroi.persistence.session.SessionMgr;
import fr.gemeroi.translation.dto.SubtitleDTO;

public class Translate {
	// TODO Handle the case of the word is followed by a point (. or ! or ...) 
	private static final String queryHqlFormat = "from Subtitle as sub1, Subtitle as sub2 where "
										 + "sub1.language = '%s' AND "
										 + "sub2.language = '%s' AND "
										 + "sub2.entityvideo.id = sub1.entityvideo.id AND "
										 + "sub2.timebegin = sub1.timebegin AND "
										 + "sub1.timebegin > 20 AND "
										 + "(lower(sub1.expression) like lower('%% %s %%') "
										 + " OR lower(sub1.expression) like lower('%s %%')  "
										 + " OR lower(sub1.expression) like lower('%% %s'))";

	public static final String userTranslationUsingWordToTranslate = 
			"%s and (sub1.id, sub2.id) in" +
			"(select ut.subtitle1, ut.subtitle2 from UsersTranslations ut where email = '%s' )";

	SessionFactory sessionFactory = SessionMgr.getSessionFactory();
	
	public Set<Translation> translate(String word, Language fromLanguage, Language toLanguage, User user) {
		Session session = sessionFactory.openSession();
		
		String queryHql = String.format(queryHqlFormat, fromLanguage.name(), toLanguage.name(), word, word, word);
		Query query = session.createQuery(queryHql);
		query.setMaxResults(30);
		List<Object[]> list = query.list();
		Set<Translation> translations = new HashSet<Translation>();

		for(Object[] resultEntity : list) {
			Subtitle subtitle1 = (Subtitle) resultEntity[0];
			Subtitle subtitle2 = (Subtitle) resultEntity[1];

			translations.add(new Translation(SubtitleDTO.createSubtitleDTO(subtitle1), SubtitleDTO.createSubtitleDTO(subtitle2), false));
				
		}

		if(user != null) {
			Set<Translation> usersTranslation = getUserTranslationInTranslationsResult(queryHql, session, user);
			for(Translation tr : usersTranslation) {
				translations.remove(tr);
				translations.add(tr);
			}
		}

		session.close();
		return translations;
	}

	private static Set<Translation> getUserTranslationInTranslationsResult(String queryHql, Session session, User user) {
		Set<Translation> userTranslations = new HashSet<Translation>();
		String queryHqlUserTranslation = String.format(userTranslationUsingWordToTranslate, queryHql, user.getEmail());
		
		Query query = session.createQuery(queryHqlUserTranslation);
		List<Object[]> list = query.list();
		
		for(Object[] resultEntity : list) {
			Subtitle subtitle1 = (Subtitle) resultEntity[0];
			Subtitle subtitle2 = (Subtitle) resultEntity[1];

			userTranslations.add(new Translation(SubtitleDTO.createSubtitleDTO(subtitle1), SubtitleDTO.createSubtitleDTO(subtitle2), true));
		}

		return userTranslations;
	}
}
