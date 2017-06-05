package fr.gemeroi.persistence.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "USERS_PERSONAL_TRANSLATIONS")
public class UsersPersonalTranslations {

	private Integer id;
	private String email;
	private String expr1;
	private String expr2;
	private String languageFrom;
	private String languageTo;

	public UsersPersonalTranslations() {
		
	}

	public UsersPersonalTranslations(String email, String expr1, String expr2, String languageFrom, String languageTo) {
		this.email = email;
		this.expr1 = expr1;
		this.expr2 = expr2;
		this.languageFrom = languageFrom;
		this.languageTo = languageTo;
	}

	@Id
	@SequenceGenerator(name="pk_sequence",sequenceName="users_personal_translations_id_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="pk_sequence")
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "EMAIL")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Column(name="EXPR1")
	public String getExpr1() {
		return expr1;
	}
	public void setExpr1(String expr1) {
		this.expr1 = expr1;
	}
	
	@Column(name="EXPR2")
	public String getExpr2() {
		return expr2;
	}
	public void setExpr2(String expr2) {
		this.expr2 = expr2;
	}

	@Column(name="LANGUAGE_FROM")
	public String getLanguageFrom() {
		return this.languageFrom;
	}
	public void setLanguageFrom(String languageFrom) {
		this.languageFrom = languageFrom;
	}

	@Column(name="LANGUAGE_TO")
	public String getLanguageTo() {
		return this.languageTo;
	}
	public void setLanguageTo(String languageTo) {
		this.languageTo = languageTo;
	}
}
