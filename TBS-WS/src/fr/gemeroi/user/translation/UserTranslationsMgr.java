package fr.gemeroi.user.translation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import fr.gemeroi.persistence.bean.User;
import fr.gemeroi.persistence.bean.UsersTranslations;
import fr.gemeroi.translation.Translation;
import fr.gemeroi.translation.dto.SubtitleDTO;

public class UserTranslationsMgr {
	public static final Map<User, Set<Translation>> userTranslations = new HashMap<>();

	public static List<Translation> convertUsersTranslationsToTranslation(List<UsersTranslations> usersTranslations) {
	  List<Translation> translationsList = new ArrayList<>();
	  for(UsersTranslations ut : usersTranslations) {
		  translationsList.add(new Translation(ut.getId(), SubtitleDTO.createSubtitleDTO(ut.getSubtitle1()), 
				               SubtitleDTO.createSubtitleDTO(ut.getSubtitle2()), true, false));
	  }
	  return translationsList;
	}
}
