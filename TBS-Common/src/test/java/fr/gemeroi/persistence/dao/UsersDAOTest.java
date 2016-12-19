package fr.gemeroi.persistence.dao;


import static org.junit.Assert.*;

import javax.inject.Inject;

import org.junit.Test;

import fr.gemeroi.persistence.dao.impl.UsersDAOImpl;

public class UsersDAOTest {

	@Inject
	UsersDAOImpl usersDAO;

	@Test
	public void retrieveUser_if_wrong_password_then_null() {
		assertTrue(usersDAO.retrieveUser("hage.jonathan@gmail.com", "bad psswd") == null);
	}

	@Test
	public void retrieveUser_if_good_password_then_user() {
		assertTrue(usersDAO.retrieveUser("hage.jonathan@gmail.com", "hello") != null);
	}

}
