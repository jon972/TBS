package fr.gemeroi.persistence.bean;

// Generated 3 oct. 2015 17:33:38 by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "ENTITY_VIDEO", uniqueConstraints = @UniqueConstraint(columnNames = {
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
	@SequenceGenerator(name="pk_sequence",sequenceName="entity_video_id_seq", allocationSize=1)
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

	public boolean equals(Object o) {
		if(o == null || o.getClass() != this.getClass()) {
			return false;
		}

		Entityvideo entityVideo = (Entityvideo) o;
		return this.nom.equals(entityVideo.nom) &&
			   this.numepisode.equals(entityVideo.numepisode) &&
			   this.numsaison.equals(entityVideo.numsaison);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		result = prime * result + ((numepisode == null) ? 0 : numepisode.hashCode());
		result = prime * result + ((numsaison == null) ? 0 : numsaison.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}
}
