function Translation(id, subDTO1, subDTO2, isSaved, isPersonalTranslation) {
	this.id = id;
	this.subtitleDTOToTranslate = subDTO1;
	this.subtitleDTOTranslated = subDTO2;
	this.isSaved = isSaved;
	this.isPersonalTranslation = isPersonalTranslation;
}