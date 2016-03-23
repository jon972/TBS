package fr.gemeroi.persistence.utils.query;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import fr.gemeroi.persistence.bean.UsersTranslations;
import fr.gemeroi.persistence.session.SessionMgr;

public class QueryUsersTranslationsUtils {

	public static List<UsersTranslations> retrieveUsersTranslations(String userMail) {
		Session session = SessionMgr.getSessionFactory().openSession();
		Criteria cr = session.createCriteria(UsersTranslations.class);
		List<UsersTranslations> listUsersTranslations = cr.add(Restrictions.eq("email", userMail)).list();
		session.close();
		return listUsersTranslations;
	}
}
