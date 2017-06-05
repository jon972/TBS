package fr.gemeroi.translation.dto;

import fr.gemeroi.persistence.bean.Subtitle;

public class SubtitleDTO {

	private Integer id;
	private String expression;
	private String language;

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((expression == null) ? 0 : expression.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((language == null) ? 0 : language.hashCode());
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
		return true;
	}
}
