package fr.gemeroi.persistence.bean.generated;

import fr.gemeroi.common.utils.LanguageEnum;

public class LanguageFactory {
	public static Language getNewLanguage(LanguageEnum languageEnum) {
		switch(languageEnum) {
		case French : return new French();
		case English : return new English();
		default : throw new IllegalStateException(languageEnum.name() + " not supported ");
		}
	}
}
