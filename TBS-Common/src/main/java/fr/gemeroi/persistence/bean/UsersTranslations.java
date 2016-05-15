package fr.gemeroi.persistence.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "USERS_TRANSLATIONS")
public class UsersTranslations {

	private Integer id;
	private String email;
	private Subtitle sub1;
	private Subtitle sub2;

	public UsersTranslations() {
		
	}

	public UsersTranslations(String email, Subtitle sub1, Subtitle sub2) {
		super();
		this.email = email;
		this.sub1 = sub1;
		this.sub2 = sub2;
	}

	@Id
	@SequenceGenerator(name="pk_sequence",sequenceName="users_translations_id_seq", allocationSize=1)
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
	
	@ManyToOne(fetch=FetchType.EAGER) 
	@JoinColumn(name="SUB_ID_1", referencedColumnName="id")
	public Subtitle getSubtitle1() {
		return sub1;
	}
	public void setSubtitle1(Subtitle expr1) {
		this.sub1 = expr1;
	}
	
	@ManyToOne(fetch=FetchType.EAGER) 
	@JoinColumn(name="SUB_ID_2", referencedColumnName="id")
	public Subtitle getSubtitle2() {
		return sub2;
	}
	public void setSubtitle2(Subtitle sub2) {
		this.sub2 = sub2;
	}
}
