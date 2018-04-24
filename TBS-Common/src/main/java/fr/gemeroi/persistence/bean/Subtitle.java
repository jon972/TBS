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
@Table(name = "SUBTITLE")
public class Subtitle implements java.io.Serializable {

	private Integer id;
	private Entityvideo entityvideo;
	private String expression;
	private Integer timebegin;
	private Integer timeend;
	private Integer rank;
	private String language;

	public Subtitle() {
	}

	public Subtitle(int id) {
		this.id = id;
	}

	public Subtitle(Entityvideo entityvideo, String expression,
			Integer timebegin, Integer timeend, Integer rank, String language) {
		this.entityvideo = entityvideo;
		this.expression = expression;
		this.timebegin = timebegin;
		this.timeend = timeend;
		this.rank = rank;
		this.language = language;
	}

	@Id
	@SequenceGenerator(name="pk_sequence",sequenceName="subtitle_id_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="pk_sequence")
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "entityvideoid")
	public Entityvideo getEntityvideo() {
		return this.entityvideo;
	}

	public void setEntityvideo(Entityvideo entityvideo) {
		this.entityvideo = entityvideo;
	}

	@Column(name = "expression")
	public String getExpression() {
		return this.expression;
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}

	@Column(name = "timebegin")
	public Integer getTimebegin() {
		return this.timebegin;
	}

	public void setTimebegin(Integer timebegin) {
		this.timebegin = timebegin;
	}

	@Column(name = "timeend")
	public Integer getTimeend() {
		return this.timeend;
	}

	public void setTimeend(Integer timeend) {
		this.timeend = timeend;
	}

	@Column(name = "rank")
	public Integer getRank() {
		return this.rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	@Column(name = "language")
	public String getLanguage() {
		return this.language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	@Override
	public String toString() {
		return this.id + " : " + this.expression + " ; " + this.timebegin + " --> " + this.timeend;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((entityvideo == null) ? 0 : entityvideo.hashCode());
		result = prime * result
				+ ((expression == null) ? 0 : expression.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((language == null) ? 0 : language.hashCode());
		result = prime * result + ((rank == null) ? 0 : rank.hashCode());
		result = prime * result
				+ ((timebegin == null) ? 0 : timebegin.hashCode());
		result = prime * result + ((timeend == null) ? 0 : timeend.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Subtitle other = (Subtitle) obj;
		if (entityvideo == null) {
			if (other.entityvideo != null)
				return false;
		} else if (!entityvideo.equals(other.entityvideo))
			return false;
		if (expression == null) {
			if (other.expression != null)
				return false;
		} else if (!expression.equals(other.expression))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (language == null) {
			if (other.language != null)
				return false;
		} else if (!language.equals(other.language))
			return false;
		if (rank == null) {
			if (other.rank != null)
				return false;
		} else if (!rank.equals(other.rank))
			return false;
		if (timebegin == null) {
			if (other.timebegin != null)
				return false;
		} else if (!timebegin.equals(other.timebegin))
			return false;
		if (timeend == null) {
			if (other.timeend != null)
				return false;
		} else if (!timeend.equals(other.timeend))
			return false;
		return true;
	}
}
