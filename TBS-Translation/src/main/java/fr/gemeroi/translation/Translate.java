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
import fr.gemeroi.persistence.bean.UsersPersonalTranslations;
import fr.gemeroi.persistence.session.SessionMgr;
import fr.gemeroi.translation.dto.SubtitleDTO;

public class Translate {

	private Translate() {}

	// TODO Handle the case of the word is followed by a point (. or ! or ...) 
	private static final String QUERY_HQL_FORMAT = "from Subtitle as sub1, Subtitle as sub2 where "
										 + "sub1.language = '%s' AND "
										 + "sub2.language = '%s' AND "
										 + "sub2.entityvideo.id = sub1.entityvideo.id AND "
										 + "sub2.timebegin = sub1.timebegin AND "
										 + "sub1.timebegin > 20 AND "
										 + "(lower(sub1.expression) like lower('%% %s %%') "
										 + " OR lower(sub1.expression) like lower('%s %%')  "
										 + " OR lower(sub1.expression) like lower('%% %s'))";

	public static final String USER_TRANSLATION_USING_WORD_TO_TRANSLATE = 
			"%s and (sub1.id, sub2.id) in" +
			"(select ut.subtitle1, ut.subtitle2 from UsersTranslations ut where email = '%s' )";
	
	private static final String USER_PERSONAL_TRANSLATIONS_QUERY = 
			"from UsersPersonalTranslations where email = '%s' and language_from = '%s' and language_to= '%s'";
	
	public static Set<Translation> translate(String word, Language fromLanguage, Language toLanguage, User user) {
		SessionFactory sessionFactory = SessionMgr.getSessionFactory();
		Session session = sessionFactory.openSession();
		
		String queryHql = String.format(QUERY_HQL_FORMAT, fromLanguage.name(), toLanguage.name(), word, word, word);
		Query query = session.createQuery(queryHql);
		query.setMaxResults(30);
		List<Object[]> list = query.list();
		Set<Translation> translations = new HashSet<>();

		for(Object[] resultEntity : list) {
			Subtitle subtitle1 = (Subtitle) resultEntity[0];
			Subtitle subtitle2 = (Subtitle) resultEntity[1];

			translations.add(new Translation(SubtitleDTO.createSubtitleDTO(subtitle1), SubtitleDTO.createSubtitleDTO(subtitle2), false, false));
				
		}

		if(user != null) {
			Set<Translation> usersTranslation = getUserTranslationInTranslationsResult(queryHql, session, user, fromLanguage.name(), toLanguage.name());
			for(Translation tr : usersTranslation) {
				translations.remove(tr);
				// TODO : Create an SQL request filtered on the word instead of retrieving all UsersTranslations
				if(tr.getSubtitleDTOToTranslate().getExpression().contains(word))
					translations.add(tr);
			}
		}

		session.close();
		return translations;
	}

	private static Set<Translation> getUserTranslationInTranslationsResult(String queryHql, Session session, User user, String languageFrom, String languageTo) {
		Set<Translation> userTranslations = new HashSet<>();
		String queryHqlUserTranslation = String.format(USER_TRANSLATION_USING_WORD_TO_TRANSLATE, queryHql, user.getEmail());

		Query query = session.createQuery(queryHqlUserTranslation);
		List<Object[]> list = query.list();
		
		for(Object[] resultEntity : list) {
			Subtitle subtitle1 = (Subtitle) resultEntity[0];
			Subtitle subtitle2 = (Subtitle) resultEntity[1];

			userTranslations.add(new Translation(SubtitleDTO.createSubtitleDTO(subtitle1), SubtitleDTO.createSubtitleDTO(subtitle2), true, false));
		}
		userTranslations.addAll(getUsersPersonalTranslation(user, languageFrom, languageTo));
		return userTranslations;
	}

	public static Set<Translation> getUsersPersonalTranslation(User user, String languageFrom, String languageTo) {
		SessionFactory sessionFactory = SessionMgr.getSessionFactory();
		Session session = sessionFactory.openSession();

		Set<Translation> userPersonalTranslations = new HashSet<>();
		
		Query query = session.createQuery(String.format(USER_PERSONAL_TRANSLATIONS_QUERY, user.getEmail(), languageFrom, languageTo));
		
		List<UsersPersonalTranslations> list = query.list();
		for(UsersPersonalTranslations resultEntity : list) {

			userPersonalTranslations.add(new Translation(resultEntity.getId(), new SubtitleDTO(resultEntity.getExpr1(), resultEntity.getLanguageFrom()), 
					new SubtitleDTO(resultEntity.getExpr2(), resultEntity.getLanguageTo()), true, true));
		}

		session.close();

		return userPersonalTranslations;
	}
}
