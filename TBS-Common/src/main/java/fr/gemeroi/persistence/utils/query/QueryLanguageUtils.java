package fr.gemeroi.persistence.utils.query;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import fr.gemeroi.common.utils.Language;
import fr.gemeroi.persistence.bean.Entityvideo;
import fr.gemeroi.persistence.bean.Subtitle;

public class QueryLanguageUtils {

	public static boolean expressionAlreadyExistForTheCurrentVideo(Entityvideo entityvideo, Session session, Language languageEnum) {
		Criteria cr = session.createCriteria(Subtitle.class);
		List<Subtitle> list = cr.add(Restrictions.eq("entityvideo", entityvideo))
								.add(Restrictions.eq("language", languageEnum.name()))
				                .list(); // TODO create a class to put table name as constant variable
		return list.size() > 0;
	}

	public static List<Subtitle> getSubtitlesFromDB(Entityvideo entityvideo, Session session, Language languageEnum) {
		Criteria cr = session.createCriteria(Subtitle.class);
		List<Subtitle> results = 
			  cr.add(Restrictions.eq("entityvideo", entityvideo))
			    .add(Restrictions.ne("language", languageEnum.name()))
			    .addOrder(Order.desc("timeend"))
				.list();
		return results;
	}

	public static void persistSubtitles(List<Subtitle> subtitles, Session session, Entityvideo entityvideo, Language languageEnum) {
		try {
			Transaction tx = session.beginTransaction();
			if (subtitles == null || subtitles.size() == 0 ||
					expressionAlreadyExistForTheCurrentVideo(entityvideo, session, languageEnum)) {
				return;
			}

			for (Subtitle sub : subtitles) {
				session.save(sub);
			}
			tx.commit();
		} catch (Exception e) {

		}
	}
}
