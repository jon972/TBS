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
		"name", "numseason", "numepisode" }))
public class Entityvideo implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String name;
	private String type;
	private Integer numseason;
	private Integer numepisode;
	private String poster;

	public Entityvideo() {
	}

	public Entityvideo(Integer id) {
		this.id = id;
	}

	public Entityvideo(String nom, String type, Integer numsaison, Integer numepisode) {
		this.name = nom;
		this.type = type;
		this.numseason = numsaison;
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

	@Column(name = "name")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "type")
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "numseason")
	public Integer getNumseason() {
		return this.numseason;
	}

	public void setNumseason(Integer numseason) {
		this.numseason = numseason;
	}

	@Column(name = "numepisode")
	public Integer getNumepisode() {
		return this.numepisode;
	}

	public void setNumepisode(Integer numepisode) {
		this.numepisode = numepisode;
	}

	@Column(name = "poster")
	public String getPoster() {
		return this.poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public boolean equals(Object o) {
		if(o == null || o.getClass() != this.getClass()) {
			return false;
		}

		Entityvideo entityVideo = (Entityvideo) o;
		return this.name.equals(entityVideo.name) &&
			   this.numepisode.equals(entityVideo.numepisode) &&
			   this.numseason.equals(entityVideo.numseason);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((numepisode == null) ? 0 : numepisode.hashCode());
		result = prime * result + ((numseason == null) ? 0 : numseason.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}
}
