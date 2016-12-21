package fr.gemeroi.user.creation;

import java.util.HashMap;
import java.util.Map;

import fr.gemeroi.persistence.bean.User;


public class UsersCache {
	private static final Map<String, User> usersCache = new HashMap<>();

	public static void addUser(String token, User user) {
		usersCache.put(token, user);
	}

	public static User getUser(String token) {
		return usersCache.get(token);
	}
}
