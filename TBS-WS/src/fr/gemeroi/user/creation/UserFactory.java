package fr.gemeroi.user.creation;

import java.util.UUID;

import fr.gemeroi.persistence.bean.User;
import fr.gemeroi.persistence.utils.PersistenceUtils;

public class UserFactory {

	private UserFactory() {}

	public static User createUser(String login, String email, String password) {
		User user = new User(login, email, password);
		PersistenceUtils.persistObject(user);

		String token = generateToken();
		UsersCache.getInstance().addUser(token, user);

		return user;
	}

	private static String generateToken() {
		return UUID.randomUUID().toString().replace("-", "");
	}
}
