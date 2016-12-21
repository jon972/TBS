package fr.gemeroi.persistence.dao.impl;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import fr.gemeroi.common.utils.Language;
import fr.gemeroi.persistence.bean.UsersTranslations;
import fr.gemeroi.persistence.dao.model.UsersTranslationsDAO;

public class UsersTranslationsDAOImpl implements UsersTranslationsDAO {

	private static Logger logger = Logger.getLogger(UsersTranslationsDAOImpl.class);

	public static final String userTranslationUsingTranslationType = 
			"from UsersTranslations ut where ut.email = '%s' and "
			+ "ut.subtitle1.language = '%s' and "
			+ "ut.subtitle2.language = '%s'";

	private SessionFactory sessionFactory;

	public UsersTranslationsDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public List<UsersTranslations> retrieveUsersTranslations(String userMail, Language l1, Language l2) {
		Session session = sessionFactory.openSession();
		String queryHql = String.format(userTranslationUsingTranslationType, userMail, l1.name(), l2.name());
		Query query = session.createQuery(queryHql);
		List<UsersTranslations> listUsersTranslations = query.list();
		session.close();
		return listUsersTranslations;
	}

	public void removeUsersTranslations(String email, int id) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Query query = session.createQuery("delete UsersTranslations where id = :ID AND email = :EMAIL");
		query.setParameter("ID", id);
		query.setParameter("EMAIL", email);
		query.executeUpdate();
		transaction.commit();
		session.close();
	}

	public void addUsersTranslations(String userMail, Language l1, Language l2, String exprFrom, String exprTo, int id) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Query query = session.createQuery("delete UsersTranslations where id = :ID AND email = :EMAIL");
		query.setParameter("ID", id);
		query.setParameter("EMAIL", userMail);
		query.executeUpdate();
		transaction.commit();
		session.close();
	}

	public void save(UsersTranslations usersTranslations) {
		Session session = sessionFactory.openSession();
		try {
			Transaction tx = session.beginTransaction();
			session.save(usersTranslations);
			tx.commit();
		} catch (Exception e) {
			logger.error("The usersTranslations " + usersTranslations + " cannot be persisted");
		} finally {
			session.close();
		}
	}
}
