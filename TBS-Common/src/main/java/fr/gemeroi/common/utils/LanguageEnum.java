package fr.gemeroi.common.utils;

import fr.gemeroi.persistence.bean.English;
import fr.gemeroi.persistence.bean.French;
import fr.gemeroi.persistence.bean.Language;


public enum LanguageEnum {
	English(English.class), French(French.class);
	
	Class<? extends Language> clazz;
	private LanguageEnum(Class<? extends Language> clazz) {
		this.clazz = clazz;
	}

	public Class<? extends Language> getClassLanguage() {
		return this.clazz;
	}

	public static LanguageEnum getInstance(String languageStr) {
		if(languageStr.toLowerCase().equals(English.name().toLowerCase())) {
			return English;
		}
		if(languageStr.toLowerCase().equals(French.name().toLowerCase())) {
			return French;
		}
		throw new IllegalArgumentException("'" + languageStr + "' is not a supported language");
	}
}
