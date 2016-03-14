package fr.gemeroi.persistence.bean;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USERS")
public class User {
	private String login;
	private String email;
	private String password;
	private String token;
	
	public User() {
	}

	public User(String login, String email, String password) {
		this.setLogin(login);
		this.password = password;
		this.email = email;
		this.token = generateToken();
	}

	public User(String email, String password) {
		this.password = password;
		this.email = email;
		this.token = generateToken();
	}

	private String generateToken() {
		return UUID.randomUUID().toString().replace("-", "");
	}

	@Id
	@Column(name = "EMAIL")
	public String getEmail() {
		return email;
	}

	@Column(name = "PASSWORD")
	public String getPassword() {
		return password;
	}

	@Column(name = "LOGIN")
	public String getLogin() {
		return login;
	}

	@Column(name = "TOKEN")
	public String getToken() {
		return token;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public void setLogin(String login) {
		this.login = login;
	}
}
