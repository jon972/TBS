package fr.gemeroi.common.utils;

public enum LanguageEnum {
	English, French;

	public static LanguageEnum getInstance(final String language) {
		final String languageToLowerCase = language.toLowerCase();
		switch (languageToLowerCase) {
		case "french" : return LanguageEnum.French;
		case "english" : return LanguageEnum.English;
		default : throw new IllegalArgumentException();
		}
	}
}
