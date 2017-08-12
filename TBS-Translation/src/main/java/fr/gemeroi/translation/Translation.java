package fr.gemeroi.translation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import fr.gemeroi.translation.dto.SubtitleDTO;

public class Translation {

	private Integer id;
	private SubtitleDTO subtitleDTOToTranslate;
	private SubtitleDTO subtitleDTOTranslated;
	private boolean isSaved;
	private boolean isPersonalTranslation;

	public Translation(Integer id, SubtitleDTO subtitleDTOToTranslate, SubtitleDTO subtitleDTOTranslated, boolean isSaved, boolean isPersonalTranslation) {
		this.subtitleDTOToTranslate = subtitleDTOToTranslate;
		this.subtitleDTOTranslated = subtitleDTOTranslated;
		this.setSaved(isSaved);
		this.setId(id);
		this.setPersonalTranslation(isPersonalTranslation);
	}

	public Translation(SubtitleDTO subtitleDTOToTranslate, SubtitleDTO subtitleDTOTranslated, boolean isSaved, boolean isPersonalTranslation) {
		this.subtitleDTOToTranslate = subtitleDTOToTranslate;
		this.subtitleDTOTranslated = subtitleDTOTranslated;
		this.setSaved(isSaved);
		this.setPersonalTranslation(isPersonalTranslation);
	}

	public static Translation valueOf(String json) {
		final GsonBuilder builder = new GsonBuilder();
		final Gson gson = builder.create();
		return gson.fromJson(json, Translation.class);
	}

	@Override
	public String toString() {
		return subtitleDTOToTranslate.getExpression() + " : " + subtitleDTOTranslated.getExpression();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public boolean isSaved() {
		return isSaved;
	}

	public void setSaved(boolean isSaved) {
		this.isSaved = isSaved;
	}

	public SubtitleDTO getSubtitleDTOToTranslate() {
		return subtitleDTOToTranslate;
	}

	public void setSubtitleDTOToTranslate(SubtitleDTO subtitleDTOToTranslate) {
		this.subtitleDTOToTranslate = subtitleDTOToTranslate;
	}

	public SubtitleDTO getSubtitleDTOTranslated() {
		return subtitleDTOTranslated;
	}

	public void setSubtitleDTOTranslated(SubtitleDTO subtitleDTOTranslated) {
		this.subtitleDTOTranslated = subtitleDTOTranslated;
	}

	public boolean isPersonalTranslation() {
		return isPersonalTranslation;
	}

	public void setPersonalTranslation(boolean isPersonalTranslation) {
		this.isPersonalTranslation = isPersonalTranslation;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime
				* result
				+ ((subtitleDTOToTranslate == null) ? 0
						: subtitleDTOToTranslate.hashCode());
		result = prime
				* result
				+ ((subtitleDTOTranslated == null) ? 0 : subtitleDTOTranslated
						.hashCode());
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
		Translation other = (Translation) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (subtitleDTOToTranslate == null) {
			if (other.subtitleDTOToTranslate != null)
				return false;
		} else if (!subtitleDTOToTranslate.equals(other.subtitleDTOToTranslate))
			return false;
		if (subtitleDTOTranslated == null) {
			if (other.subtitleDTOTranslated != null)
				return false;
		} else if (!subtitleDTOTranslated.equals(other.subtitleDTOTranslated))
			return false;
		return true;
	}
}
