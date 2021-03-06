package fr.gemeroi.persistence.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import fr.gemeroi.persistence.bean.User;
import fr.gemeroi.persistence.session.SessionMgr;

public class UsersDAO {

	private UsersDAO(){}

	public static User retrieveUser(String email, String password) {
		User user = new User(email, password);
		Session session = SessionMgr.getSessionFactory().openSession();
		Criteria cr = session.createCriteria(User.class);
		List<User> listUser = cr.add(Restrictions.eq("email", user.getEmail()))
				                .add(Restrictions.eq("password", user.getPassword()))
				                .list();
		session.close();
		return listUser.isEmpty() ? null : listUser.get(0);
	}
}
