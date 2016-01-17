package fr.gemeroi.population.utils;

import java.util.List;

import fr.gemeroi.persistence.bean.Language;

public class ValidationUtils {
	private static boolean validateSubtitles(List<Language> list1, List<Language> list2) {
		int numberLanguageMatching = 0;
		for(Language languageEntry : list1) {
			if(list2.contains(languageEntry)) {
				numberLanguageMatching ++;
			}
		}
		return false;
	}
}
