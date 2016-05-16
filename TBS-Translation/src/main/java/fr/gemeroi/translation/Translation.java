package fr.gemeroi.translation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import fr.gemeroi.translation.dto.SubtitleDTO;

public class Translation {

	private Integer id;
	private SubtitleDTO subtitleDTOToTranslate;
	private SubtitleDTO subtitleDTOTranslated;
	private boolean isSaved;

	public Translation(Integer id, SubtitleDTO subtitleDTOToTranslate, SubtitleDTO subtitleDTOTranslated, boolean isSaved) {
		this.subtitleDTOToTranslate = subtitleDTOToTranslate;
		this.subtitleDTOTranslated = subtitleDTOTranslated;
		this.setSaved(isSaved);
		this.setId(id);
	}

	public Translation(SubtitleDTO subtitleDTOToTranslate, SubtitleDTO subtitleDTOTranslated, boolean isSaved) {
		this.subtitleDTOToTranslate = subtitleDTOToTranslate;
		this.subtitleDTOTranslated = subtitleDTOTranslated;
		this.setSaved(isSaved);
	}

	public static Translation valueOf(String json) {
		final GsonBuilder builder = new GsonBuilder();
		final Gson gson = builder.create();
		Translation translation = gson.fromJson(json, Translation.class);
		return translation;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + (isSaved ? 1231 : 1237);
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
		if (isSaved != other.isSaved)
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
