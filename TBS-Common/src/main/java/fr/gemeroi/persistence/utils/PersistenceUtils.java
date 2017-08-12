package fr.gemeroi.persistence.utils;


import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import fr.gemeroi.persistence.session.SessionMgr;

public class PersistenceUtils {
	private static Logger logger = Logger.getLogger(PersistenceUtils.class);

	private PersistenceUtils(){}

	public static void persistObject(Object object) {
		SessionFactory sessionFactory = SessionMgr.getSessionFactory();
		Session session = sessionFactory.openSession();
		try {
			Transaction tx = session.beginTransaction();
			session.save(object);
			tx.commit();
		} catch (Exception e) {
			logger.error("The object " + object + " cannot be persisted");
		} finally {
			session.close();
		}
	}
}
