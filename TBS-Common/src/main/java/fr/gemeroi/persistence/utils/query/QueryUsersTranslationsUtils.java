package fr.gemeroi.persistence.utils.query;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import fr.gemeroi.common.utils.Language;
import fr.gemeroi.persistence.bean.UsersTranslations;
import fr.gemeroi.persistence.session.SessionMgr;

public class QueryUsersTranslationsUtils {

	public static final String userTranslationUsingTranslationType = 
			"from UsersTranslations ut where ut.email = '%s' and "
			+ "ut.subtitle1.language = '%s' and "
			+ "ut.subtitle2.language = '%s'";

	public static List<UsersTranslations> retrieveUsersTranslations(String userMail, Language l1, Language l2) {
		Session session = SessionMgr.getSessionFactory().openSession();
		String queryHql = String.format(userTranslationUsingTranslationType, userMail, l1.name(), l2.name());
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

	public static void addUsersTranslations(String userMail, Language l1, Language l2, String exprFrom, String exprTo, int id) {
		Session session = SessionMgr.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		Query query = session.createQuery("delete UsersTranslations where id = :ID AND email = :EMAIL");
		query.setParameter("ID", id);
		query.setParameter("EMAIL", userMail);
		query.executeUpdate();
		transaction.commit();
		session.close();
	}
}
