package fr.gemeroi.session;

import java.util.HashMap;
import java.util.Map;

import fr.gemeroi.persistence.bean.User;

public class TokenMgr {
	public static final Map<String, User> tokensMap = new HashMap<>();
}
