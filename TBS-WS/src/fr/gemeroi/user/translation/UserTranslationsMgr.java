package fr.gemeroi.user.translation;

import java.util.ArrayList;
import java.util.List;

import fr.gemeroi.persistence.bean.UsersTranslations;
import fr.gemeroi.translation.Translation;
import fr.gemeroi.translation.dto.SubtitleDTO;

public class UserTranslationsMgr {

	private UserTranslationsMgr() {}

	public static List<Translation> convertUsersTranslationsToTranslation(List<UsersTranslations> usersTranslations) {
	  List<Translation> translationsList = new ArrayList<>();
	  for(UsersTranslations ut : usersTranslations) {
		  translationsList.add(new Translation(ut.getId(), SubtitleDTO.createSubtitleDTO(ut.getSubtitle1()), 
				               SubtitleDTO.createSubtitleDTO(ut.getSubtitle2()), true, false));
	  }
	  return translationsList;
	}
}
