package fr.gemeroi.persistence.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import fr.gemeroi.common.utils.Language;
import fr.gemeroi.persistence.bean.UsersTranslations;
import fr.gemeroi.persistence.session.SessionMgr;

public class UsersTranslationsDAO {

	private UsersTranslationsDAO() {}

	public static final String USER_TRANSLATION_USING_TRANSLATION_TYPE = 
			"from UsersTranslations ut where ut.email = '%s' and "
			+ "ut.subtitle1.language = '%s' and "
			+ "ut.subtitle2.language = '%s'";

	public static final String USER_TRANSLATION_USING_ENTITY_VIDEO = 
			"from UsersTranslations ut where ut.email = '%s' and "
			+ "ut.subtitle1.language = '%s' and "
			+ "ut.subtitle2.language = '%s' and "
			+ "ut.subtitle1.entityvideo.name = '%s' and "
			+ "ut.subtitle2.entityvideo.name = '%s'";

	public static List<UsersTranslations> retrieveUsersTranslations(String userMail, Language languageInitial, Language languageDestination) {
		Session session = SessionMgr.getSessionFactory().openSession();
		String queryHql = String.format(USER_TRANSLATION_USING_TRANSLATION_TYPE, userMail, languageInitial.name(), languageDestination.name());
		Query query = session.createQuery(queryHql);

		List<UsersTranslations> listUsersTranslations = query.list();
		session.close();
		return listUsersTranslations;
	}

	public static List<UsersTranslations> retrieveUsersTranslationsOfSpecificEntityVideo(String userMail, Language languageInitial, Language languageDestination, String entityVideoName) {
		Session session = SessionMgr.getSessionFactory().openSession();
		String queryHql = String.format(USER_TRANSLATION_USING_ENTITY_VIDEO, userMail, languageInitial.name(), languageDestination.name(), entityVideoName, entityVideoName);
		Query query = session.createQuery(queryHql);

		List<UsersTranslations> listUsersTranslations = query.list();
		session.close();
		return listUsersTranslations;
	}

	public static void removeUsersTranslations(String email, int id) {
		Session session = SessionMgr.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		Query query = session.createQuery("delete UsersTranslations where id = :ID AND email = :EMAIL");
		query.setParameter("ID", id);
		query.setParameter("EMAIL", email);
		query.executeUpdate();
		transaction.commit();
		session.close();
	}

}
