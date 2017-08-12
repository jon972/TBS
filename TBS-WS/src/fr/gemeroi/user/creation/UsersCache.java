package fr.gemeroi.user.creation;

import java.util.HashMap;
import java.util.Map;

import fr.gemeroi.persistence.bean.User;

public class UsersCache {
	private static final UsersCache usersCache = new UsersCache();
	private final Map<String, User> cache = new HashMap<>();

	private UsersCache() {}

	public static UsersCache getInstance() {
		return usersCache;
	}

	public void addUser(String token, User user) {
		cache.put(token, user);
	}

	public User getUser(String token) {
		return cache.get(token);
	}
}
