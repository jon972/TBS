package fr.gemeroi.persistence.utils.query;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import fr.gemeroi.persistence.bean.User;
import fr.gemeroi.persistence.session.SessionMgr;

public class QueryUsersUtils {

	public static User retrieveUser(String email, String password) {
		User user = new User(email, password);
		Session session = SessionMgr.getSessionFactory().openSession();
		Criteria cr = session.createCriteria(User.class);
		List<User> listUser = cr.add(Restrictions.eq("email", user.getEmail()))
				                .add(Restrictions.eq("password", user.getPassword()))
				                .list();
		session.close();
		return listUser.size() > 0 ? listUser.get(0) : null;
	}
}
