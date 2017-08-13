package fr.gemeroi.persistence.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import fr.gemeroi.common.utils.Language;
import fr.gemeroi.persistence.bean.Entityvideo;
import fr.gemeroi.persistence.bean.Subtitle;
import fr.gemeroi.persistence.session.SessionMgr;

public class SubtitleDAO {

	private static final Logger LOGGER = Logger.getLogger(SubtitleDAO.class);
	private SubtitleDAO(){}

	public static Subtitle getSubtitleById(Integer id) {
		Session session = SessionMgr.getSessionFactory().openSession();
		Subtitle subtitle = (Subtitle) session.get(Subtitle.class, id);
		session.close();
		return subtitle;
	}

	public static boolean expressionAlreadyExistForTheCurrentVideo(Entityvideo entityvideo, Language languageEnum) {
		Session session = SessionMgr.getSessionFactory().openSession();
		Criteria cr = session.createCriteria(Subtitle.class);
		List<Subtitle> list = cr.add(Restrictions.eq("entityvideo", entityvideo))
								.add(Restrictions.eq("language", languageEnum.name()))
				                .list(); // TODO create a class to put table name as constant variable
		session.close();
		return !list.isEmpty();
	}

	public static List<Subtitle> retrieveSubtitles(Entityvideo entityvideo, Language languageEnum) {
		Session session = SessionMgr.getSessionFactory().openSession();
		Criteria cr = session.createCriteria(Subtitle.class);
		List<Subtitle> results = 
			  cr.add(Restrictions.eq("entityvideo", entityvideo))
			    .add(Restrictions.ne("language", languageEnum.name()))
			    .addOrder(Order.desc("timeend"))
				.list();
		session.close();
		return results;
	}

	public static void persistSubtitles(List<Subtitle> subtitles, Entityvideo entityvideo, Language languageEnum) {
		try {
			Session session = SessionMgr.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			if (subtitles == null || subtitles.isEmpty() ||
					expressionAlreadyExistForTheCurrentVideo(entityvideo, languageEnum)) {
				return;
			}

			for (Subtitle sub : subtitles) {
				session.save(sub);
			}
			tx.commit();
			session.close();
		} catch (Exception e) {
			LOGGER.warn("Subtitle persitence failed for " + subtitles);
		}
	}
}
