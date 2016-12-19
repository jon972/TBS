package fr.gemeroi.persistence.dao.model;

import fr.gemeroi.persistence.bean.User;

public interface UsersDAO {

	public User retrieveUser(String email, String password);
	public void save(User user);
}
