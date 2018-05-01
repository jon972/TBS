package fr.gemeroi.translation.dto;

import fr.gemeroi.persistence.bean.Subtitle;

public class SubtitleDTO {

	private Integer id;
	private String expression;
	private String language;
	private Integer entityvideoId;
	private Integer timebegin;
	private Integer timeend;
	private Integer rank;

	public SubtitleDTO(String expression, String language) {
		this.expression = expression;
		this.language = language;
	}
	
	public SubtitleDTO() {
		
	}

	public static SubtitleDTO createSubtitleDTO(Subtitle subtitle) {
		SubtitleDTO subtitleDTO = new SubtitleDTO();
		subtitleDTO.setId(subtitle.getId());
		subtitleDTO.setExpression(subtitle.getExpression());
		subtitleDTO.setLanguage(subtitle.getLanguage());
		subtitleDTO.setRank(subtitle.getRank());
		subtitleDTO.setEntityvideoId(subtitle.getEntityvideo().getId());
		subtitleDTO.setTimebegin(subtitle.getTimebegin());
		subtitleDTO.setTimeend(subtitle.getTimeend());
		return subtitleDTO;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getExpression() {
		return expression;
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public Integer getEntityvideoId() {
		return entityvideoId;
	}

	public void setEntityvideoId(Integer entityvideoId) {
		this.entityvideoId = entityvideoId;
	}

	public Integer getTimebegin() {
		return timebegin;
	}

	public void setTimebegin(Integer timebegin) {
		this.timebegin = timebegin;
	}

	public Integer getTimeend() {
		return timeend;
	}

	public void setTimeend(Integer timeend) {
		this.timeend = timeend;
	}

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((entityvideoId == null) ? 0 : entityvideoId.hashCode());
		result = prime * result + ((expression == null) ? 0 : expression.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((language == null) ? 0 : language.hashCode());
		result = prime * result + ((rank == null) ? 0 : rank.hashCode());
		result = prime * result + ((timebegin == null) ? 0 : timebegin.hashCode());
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
		SubtitleDTO other = (SubtitleDTO) obj;
		if (entityvideoId == null) {
			if (other.entityvideoId != null)
				return false;
		} else if (!entityvideoId.equals(other.entityvideoId))
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
