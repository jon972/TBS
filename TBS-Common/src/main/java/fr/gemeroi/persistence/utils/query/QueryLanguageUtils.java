package fr.gemeroi.persistence.utils.query;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import fr.gemeroi.persistence.bean.Entityvideo;
import fr.gemeroi.persistence.bean.Language;

public class QueryLanguageUtils {

	public static void sortLanguageByTimeEndSubtitle(List<Language> listLanguage) {
		Collections.sort(listLanguage, new Comparator<Language>() {
			public int compare(Language fr1, Language fr2) {
				if (fr1.getTimeend() > fr2.getTimeend())
					return -1;
				if (fr1.getTimeend() < fr2.getTimeend())
					return 1;
				return 0;
			}
		});
	}

	public static boolean expressionAlreadyExistForTheCurrentVideo(Entityvideo entityvideo, Session session, Class<? extends Language> class1) {
		Criteria cr = session.createCriteria(class1);
		List<Language> list = cr.add(Restrictions.eq("entityvideo", entityvideo)).list();
		return list.size() > 0;
	}

	public static List<Language> getListLanguageFromDB(Entityvideo entityvideo,
			Class<? extends Language> clazz, Session session) {
		Criteria cr = session.createCriteria(clazz);
		List<Language> results = 
			  cr.add(Restrictions.eq("entityvideo", entityvideo))
				.list();
		sortLanguageByTimeEndSubtitle(results);
		// System.out.println(results.get(0));
		return results;
	}

	public static void persistLanguage(List<Language> listLanguage, Session session) {
		try {
			Transaction tx = session.beginTransaction();
			if(listLanguage == null || listLanguage.size() == 0 || 
					expressionAlreadyExistForTheCurrentVideo(listLanguage.get(0).getEntityvideo(), session, listLanguage.get(0).getClass())) {
				return;
			}
			for (Language fr : listLanguage) {
				session.save(fr);
			}
			tx.commit();
		} catch (Exception e) {

		}
	}
}
