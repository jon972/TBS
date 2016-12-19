package fr.gemeroi.user.creation;

import java.util.UUID;

import javax.inject.Inject;
import fr.gemeroi.persistence.bean.User;
import fr.gemeroi.persistence.dao.model.UsersDAO;

public class UserFactory {

	@Inject
	UsersDAO userDao;

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
