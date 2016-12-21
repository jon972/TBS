package fr.gemeroi.persistence.dao;


import static org.junit.Assert.assertTrue;

import org.junit.Test;

import fr.gemeroi.configuration.Configuration;
import fr.gemeroi.configuration.TBSConfigurationForTests;
import fr.gemeroi.persistence.dao.impl.UsersDAOImpl;
import fr.gemeroi.persistence.dao.model.UsersDAO;

public class UsersDAOTest {

	private UsersDAO usersDAO;

	public UsersDAOTest() {
		Configuration configuration = new TBSConfigurationForTests();
		usersDAO = new UsersDAOImpl(configuration.getSessionFactory());
	}

	@Test
	public void retrieveUser_if_wrong_password_then_null() {
		assertTrue(usersDAO.retrieveUser("hage.jonathan@gmail.com", "bad psswd") == null);
	}

	@Test
	public void retrieveUser_if_good_password_then_user() {
		assertTrue(usersDAO.retrieveUser("hage.jonathan@gmail.com", "hello") != null);
	}

}
