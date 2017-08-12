package fr.gemeroi.persistence.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import fr.gemeroi.persistence.session.SessionMgr;

public class UsersPersonalTranslationsDao {

	private UsersPersonalTranslationsDao() {}

	public static void removeUsersPersonalTranslations(String email, int id) {
		Session session = SessionMgr.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		Query query = session.createQuery("delete UsersPersonalTranslations where id = :ID AND email = :EMAIL");
		query.setParameter("ID", id);
		query.setParameter("EMAIL", email);
		query.executeUpdate();
		transaction.commit();
		session.close();
	}
}
