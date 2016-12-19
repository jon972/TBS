package fr.gemeroi.persistence.dao.model;

import java.util.List;

import fr.gemeroi.common.utils.Language;
import fr.gemeroi.persistence.bean.UsersTranslations;

public interface UsersTranslationsDAO {
	public List<UsersTranslations> retrieveUsersTranslations(String userMail, Language l1, Language l2);
	public void removeUsersTranslations(String email, int id);
	public void addUsersTranslations(String userMail, Language l1, Language l2, String exprFrom, String exprTo, int id);
	public void save(UsersTranslations usersTranslations);
}
