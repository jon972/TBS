package fr.gemeroi.persistence.utils.query;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import fr.gemeroi.persistence.bean.UsersTranslations;
import fr.gemeroi.persistence.session.SessionMgr;

public class QueryUsersTranslationsUtils {

	public static List<UsersTranslations> retrieveUsersTranslations(String userMail) {
		Session session = SessionMgr.getSessionFactory().openSession();
		Criteria cr = session.createCriteria(UsersTranslations.class);
		List<UsersTranslations> listUsersTranslations = cr.add(Restrictions.eq("email", userMail)).list();
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
