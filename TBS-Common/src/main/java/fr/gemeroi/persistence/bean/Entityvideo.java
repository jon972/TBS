package fr.gemeroi.persistence.bean;

// Generated 3 oct. 2015 17:33:38 by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Entityvideo generated by hbm2java
 */
@Entity
@Table(name = "entityvideo", uniqueConstraints = @UniqueConstraint(columnNames = {
		"nom", "numsaison", "numepisode" }))
public class Entityvideo implements java.io.Serializable {

	private Integer id;
	private String nom;
	private String type;
	private Integer numsaison;
	private Integer numepisode;

	public Entityvideo() {
	}

	public Entityvideo(Integer id) {
		this.id = id;
	}

	public Entityvideo(String nom, String type, Integer numsaison, Integer numepisode) {
		this.nom = nom;
		this.type = type;
		this.numsaison = numsaison;
		this.numepisode = numepisode;
	}

	@Id
	@SequenceGenerator(name="pk_sequence",sequenceName="entityvideo_id_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="pk_sequence")
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "nom")
	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	@Column(name = "type")
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "numsaison")
	public Integer getNumsaison() {
		return this.numsaison;
	}

	public void setNumsaison(Integer numsaison) {
		this.numsaison = numsaison;
	}

	@Column(name = "numepisode")
	public Integer getNumepisode() {
		return this.numepisode;
	}

	public void setNumepisode(Integer numepisode) {
		this.numepisode = numepisode;
	}
}
