package fr.gemeroi.persistence.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "USERSTRANSLATIONS")
public class UsersTranslations {

	private String userLogin;
	private Integer entityvideoid;
	private String language1;
	private String language2;
	private Integer timebegin;

	public UsersTranslations(String userLogin, Integer entityvideoid,
			String language1, String language2, Integer timebegin) {
		super();
		this.userLogin = userLogin;
		this.entityvideoid = entityvideoid;
		this.language1 = language1;
		this.language2 = language2;
		this.timebegin = timebegin;
	}

	@Column(name = "userlogin")
	public String getUserLogin() {
		return userLogin;
	}
	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
	}
	
	@Column(name = "entityvideoid")
	public Integer getEntityvideoid() {
		return entityvideoid;
	}
	public void setEntityvideoid(Integer entityvideoid) {
		this.entityvideoid = entityvideoid;
	}
	
	@Column(name = "language1")
	public String getLanguage1() {
		return language1;
	}
	public void setLanguage1(String language1) {
		this.language1 = language1;
	}
	
	@Column(name = "language2")
	public String getLanguage2() {
		return language2;
	}
	public void setLanguage2(String language2) {
		this.language2 = language2;
	}
	
	@Column(name = "timebegin")
	public Integer getTimebegin() {
		return timebegin;
	}
	public void setTimebegin(Integer timebegin) {
		this.timebegin = timebegin;
	}
}
