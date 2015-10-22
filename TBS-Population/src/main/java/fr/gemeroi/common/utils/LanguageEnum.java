package fr.gemeroi.common.utils;

import fr.gemeroi.persistence.bean.generated.English;
import fr.gemeroi.persistence.bean.generated.French;
import fr.gemeroi.persistence.bean.generated.Language;


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
		if(languageStr.equals(English.name())) {
			return English;
		}
		if(languageStr.equals(French.name())) {
			return French;
		}
		throw new IllegalArgumentException("'" + languageStr + "' is not a supported language");
	}
}
