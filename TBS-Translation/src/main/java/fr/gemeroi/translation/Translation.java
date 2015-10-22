package fr.gemeroi.translation;

public class Translation {

	private String exprToTranslate;
	private String exprTranslated;

	public Translation(String exprToTranslate, String exprTranslated) {
		this.exprToTranslate = exprToTranslate;
		this.exprTranslated = exprTranslated;
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

	@Override
	public String toString() {
		return exprToTranslate + " : " + exprTranslated;
	}
}
