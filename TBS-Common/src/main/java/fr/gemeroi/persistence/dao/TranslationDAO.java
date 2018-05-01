package fr.gemeroi.persistence.dao;

import java.util.ArrayList;
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
import fr.gemeroi.persistence.bean.UsersTranslations;
import fr.gemeroi.persistence.session.SessionMgr;
import fr.gemeroi.translation.Translation;
import fr.gemeroi.translation.dto.SubtitleDTO;

public class TranslationDAO {

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

	private static final String SELECT_SUBTITLES_AROUND = "from Subtitle as sub1, Subtitle as sub2 where "
			 + "sub1.language = '%s' AND "
			 + "sub2.language = '%s' AND "
			 + "sub1.entityvideo.id = %s AND "
			 + "sub2.entityvideo.id = %s AND "
			 + "sub2.timebegin = sub1.timebegin AND "
			 + "sub1.timebegin %s %s ORDER BY sub1.timebegin %s";

	public static final String SELECT_USER_TRANSLATION = 
			"from UsersTranslations ut where email = '%s'";
	
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
			Set<Translation> usersTranslation = getUserTranslations(session, user, fromLanguage.name(), toLanguage.name());
			for(Translation tr : translations) {
				if(usersTranslation.contains(tr))
					tr.setSaved(true);
			}
		}

		session.close();
		return translations;
	}

	private static Set<Translation> getUserTranslations(Session session, User user, String languageFrom, String languageTo) {
		Set<Translation> userTranslations = new HashSet<>();
		String queryHqlUserTranslation = String.format(SELECT_USER_TRANSLATION, user.getEmail());

		Query query = session.createQuery(queryHqlUserTranslation);
		List<UsersTranslations> list = query.list();

		for(UsersTranslations resultEntity : list) {
			Subtitle subtitle1 = resultEntity.getSubtitle1();
			Subtitle subtitle2 = resultEntity.getSubtitle2();

			userTranslations.add(new Translation(SubtitleDTO.createSubtitleDTO(subtitle1), SubtitleDTO.createSubtitleDTO(subtitle2), true, false));
		}
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

	public static Set<Translation> getSubtitlesAfter(User user, Translation translation, int translationCount) {
		String queryHqlUserTranslation = String.format(SELECT_SUBTITLES_AROUND, 
				translation.getSubtitleDTOToTranslate().getLanguage(), translation.getSubtitleDTOTranslated().getLanguage(), 
				translation.getSubtitleDTOToTranslate().getEntityvideoId(), translation.getSubtitleDTOTranslated().getEntityvideoId(),
				">=", translation.getSubtitleDTOToTranslate().getTimebegin(), "ASC");
		SessionFactory sessionFactory = SessionMgr.getSessionFactory();
		Session session = sessionFactory.openSession();

		Query query = session.createQuery(queryHqlUserTranslation);
		query.setMaxResults(translationCount);
		List<Object[]> list = query.list();
		Set<Translation> translations = new HashSet<>();

		for(Object[] resultEntity : list) {
			Subtitle subtitle1 = (Subtitle) resultEntity[0];
			Subtitle subtitle2 = (Subtitle) resultEntity[1];

			translations.add(new Translation(SubtitleDTO.createSubtitleDTO(subtitle1), SubtitleDTO.createSubtitleDTO(subtitle2), false, false));

		}

		if(user != null) {
			Set<Translation> usersTranslation = getUserTranslations(session, user, 
					translation.getSubtitleDTOToTranslate().getLanguage(), translation.getSubtitleDTOTranslated().getLanguage());
				for(Translation tr : translations) {
					if(usersTranslation.contains(tr)) {
						tr.setSaved(true);
					}
				}
		}

		session.close();
		return translations;
	}

	public static Set<Translation> getSubtitlesBefore(User user, Translation translation, int translationsCount) {
		String queryHqlUserTranslation = String.format(SELECT_SUBTITLES_AROUND, 
				translation.getSubtitleDTOToTranslate().getLanguage(), translation.getSubtitleDTOTranslated().getLanguage(), 
				translation.getSubtitleDTOToTranslate().getEntityvideoId(), translation.getSubtitleDTOTranslated().getEntityvideoId(),
				"<", translation.getSubtitleDTOToTranslate().getTimebegin(), "DESC");
		SessionFactory sessionFactory = SessionMgr.getSessionFactory();
		Session session = sessionFactory.openSession();

		Query query = session.createQuery(queryHqlUserTranslation);
		query.setMaxResults(translationsCount);
		List<Object[]> list = query.list();
		Set<Translation> translations = new HashSet<>();

		for(Object[] resultEntity : list) {
			Subtitle subtitle1 = (Subtitle) resultEntity[0];
			Subtitle subtitle2 = (Subtitle) resultEntity[1];

			translations.add(new Translation(SubtitleDTO.createSubtitleDTO(subtitle1), SubtitleDTO.createSubtitleDTO(subtitle2), false, false));
				
		}

		if(user != null) {
			Set<Translation> usersTranslation = getUserTranslations(session, user, 
					translation.getSubtitleDTOToTranslate().getLanguage(), translation.getSubtitleDTOTranslated().getLanguage());
				for(Translation tr : translations) {
					if(usersTranslation.contains(tr)) {
						tr.setSaved(true);
					}
				}
		}

		session.close();
		return translations;
	}
}
