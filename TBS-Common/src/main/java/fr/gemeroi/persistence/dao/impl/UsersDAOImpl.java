package fr.gemeroi.persistence.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import fr.gemeroi.persistence.bean.User;
import fr.gemeroi.persistence.dao.model.UsersDAO;

public class UsersDAOImpl implements UsersDAO {

	private static Logger logger = Logger.getLogger(UsersDAOImpl.class);
	private SessionFactory sessionFactory;

	public UsersDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public User retrieveUser(String email, String password) {
		User user = new User(email, password);
		Session session = sessionFactory.openSession();
		Criteria cr = session.createCriteria(User.class);
		List<User> listUser = cr.add(Restrictions.eq("email", user.getEmail()))
				                .add(Restrictions.eq("password", user.getPassword()))
				                .list();
		session.close();
		return listUser.size() > 0 ? listUser.get(0) : null;
	}
	
	public void save(User user) {
		Session session = sessionFactory.openSession();
		try {
			Transaction tx = session.beginTransaction();
			session.save(user);
			tx.commit();
		} catch (Exception e) {
			logger.error("The user " + user + " cannot be persisted");
		} finally {
			session.close();
		}
	}
}
