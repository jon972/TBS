package fr.gemeroi.persistence.utils;

import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import fr.gemeroi.persistence.session.SessionMgr;

public class PersistenceUtils {
	public static void persistObject(Object object) {
		SessionFactory sessionFactory = SessionMgr.getSessionFactory();
		Session session = sessionFactory.openSession();
		try {
			Transaction tx = session.beginTransaction();
			session.save(object);
			tx.commit();
		} catch (Exception e) {
		} finally {
			session.close();
		}
	}

	// To move
	public static String getToken() {
		return UUID.randomUUID().toString().replace("-", "");
	}

}
