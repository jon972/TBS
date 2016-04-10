package fr.gemeroi.session;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import fr.gemeroi.persistence.bean.User;
import fr.gemeroi.persistence.bean.UsersTranslations;
import fr.gemeroi.translation.Translation;

public class UserTranslationsMgr {
	public static final Map<User, Set<Translation>> userTranslations = new HashMap<>();

	public static List<Translation> convertUsersTranslationsToTranslation(List<UsersTranslations> usersTranslations) {
	  List<Translation> translationsList = new ArrayList<>();
	  for(UsersTranslations ut : usersTranslations) {
		  translationsList.add(new Translation(ut.getId(), ut.getExpr1(), ut.getExpr2(), true));
	  }
	  return translationsList;
	}

	public static void updateUserTranslations(User user, UsersTranslations userTranslation) {
		userTranslations.get(user).add(new Translation(userTranslation.getId(), userTranslation.getExpr1(), userTranslation.getExpr2(), true));
	}
}
