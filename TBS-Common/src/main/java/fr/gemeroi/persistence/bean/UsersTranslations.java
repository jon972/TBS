package fr.gemeroi.persistence.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "USERS_TRANSLATIONS")
public class UsersTranslations {

	private Integer id;
	private String email;
	private String expr1;
	private String expr2;

	public UsersTranslations() {
		
	}

	public UsersTranslations(String email, String expr1, String expr2) {
		super();
		this.email = email;
		this.expr1 = expr1;
		this.expr2 = expr2;
	}

	@Id
	@SequenceGenerator(name="pk_sequence",sequenceName="usersTranslations_id_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="pk_sequence")
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "email")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Column(name = "expr1")
	public String getExpr1() {
		return expr1;
	}
	public void setExpr1(String expr1) {
		this.expr1 = expr1;
	}
	
	@Column(name = "expr2")
	public String getExpr2() {
		return expr2;
	}
	public void setExpr2(String expr2) {
		this.expr2 = expr2;
	}
}
