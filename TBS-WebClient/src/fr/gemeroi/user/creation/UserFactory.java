package fr.gemeroi.user.creation;

import java.util.UUID;

import fr.gemeroi.persistence.bean.User;
import fr.gemeroi.persistence.dao.model.UsersDAO;

public class UserFactory {

	private UsersDAO userDao;
	
	public UserFactory(UsersDAO userDao) {
		this.userDao = userDao;
	}

	public User createUser(String login, String email, String password) {
		User user = new User(login, email, password);

		userDao.save(user);

		String token = generateToken();
		UsersCache.addUser(token, user);

		return user;
	}

	private static String generateToken() {
		return UUID.randomUUID().toString().replace("-", "");
	}
}
