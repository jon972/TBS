package fr.gemeroi.translation;

public class Translation {

	private Integer id;
	private String exprToTranslate;
	private String exprTranslated;
	private boolean isSaved;

	public Translation(Integer id, String exprToTranslate, String exprTranslated, boolean isSaved) {
		this.exprToTranslate = exprToTranslate;
		this.exprTranslated = exprTranslated;
		this.setSaved(isSaved);
		this.setId(id);
	}

	public Translation(String exprToTranslate, String exprTranslated, boolean isSaved) {
		this.exprToTranslate = exprToTranslate;
		this.exprTranslated = exprTranslated;
		this.setSaved(isSaved);
	}

	public String getExprToTranslate() {
		return exprToTranslate;
	}

	public void setExprToTranslate(String exprToTranslate) {
		this.exprToTranslate = exprToTranslate;
	}

	public String getExprTranslated() {
		return exprTranslated;
	}

	public void setExprTranslated(String exprTranslated) {
		this.exprTranslated = exprTranslated;
	}

	public boolean isSaved() {
		return isSaved;
	}

	public void setSaved(boolean isSaved) {
		this.isSaved = isSaved;
	}

	public Integer getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return exprToTranslate + " : " + exprTranslated;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((exprToTranslate == null) ? 0 : exprToTranslate.hashCode());
		result = prime * result
				+ ((exprTranslated == null) ? 0 : exprTranslated.hashCode());
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
		if (exprToTranslate == null) {
			if (other.exprToTranslate != null)
				return false;
		} else if (!exprToTranslate.equals(other.exprToTranslate))
			return false;
		if (exprTranslated == null) {
			if (other.exprTranslated != null)
				return false;
		} else if (!exprTranslated.equals(other.exprTranslated))
			return false;
		return true;
	}

}
