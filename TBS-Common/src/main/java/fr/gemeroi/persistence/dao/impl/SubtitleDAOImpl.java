package fr.gemeroi.persistence.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import fr.gemeroi.common.utils.Language;
import fr.gemeroi.persistence.bean.Entityvideo;
import fr.gemeroi.persistence.bean.Subtitle;
import fr.gemeroi.persistence.dao.model.SubtitleDAO;

public class SubtitleDAOImpl implements SubtitleDAO {

	private static Logger logger = Logger.getLogger(SubtitleDAOImpl.class);
	private SessionFactory sessionFactory;

	public SubtitleDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Subtitle getSubtitleById(Integer id) {
		Session session = sessionFactory.openSession();
		Subtitle subtitle = (Subtitle) session.get(Subtitle.class, id);
		session.close();
		return subtitle;
	}

	public boolean expressionAlreadyExistForTheCurrentVideo(Entityvideo entityvideo, Language languageEnum) {
		Session session = sessionFactory.openSession();
		Criteria cr = session.createCriteria(Subtitle.class);
		List<Subtitle> list = cr.add(Restrictions.eq("entityvideo", entityvideo))
								.add(Restrictions.eq("language", languageEnum.name()))
				                .list(); // TODO create a class to put table name as constant variable
		session.close();
		return list.size() > 0;
	}

	public List<Subtitle> getSubtitlesFromDB(Entityvideo entityvideo, Language languageEnum) {
		Session session = sessionFactory.openSession();
		Criteria cr = session.createCriteria(Subtitle.class);
		List<Subtitle> results = 
			  cr.add(Restrictions.eq("entityvideo", entityvideo))
			    .add(Restrictions.ne("language", languageEnum.name()))
			    .addOrder(Order.desc("timeend"))
				.list();
		session.close();
		return results;
	}

	public void persistSubtitles(List<Subtitle> subtitles, Entityvideo entityvideo, Language languageEnum) {
		try {
			if (subtitles == null || subtitles.size() == 0 ||
					expressionAlreadyExistForTheCurrentVideo(entityvideo, languageEnum)) {
				return;
			}

			for (Subtitle sub : subtitles) {
				save(sub);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	public void save(Subtitle subtitle) {
		Session session = sessionFactory.openSession();
		try {
			Transaction tx = session.beginTransaction();
			session.save(subtitle);
			tx.commit();
		} catch (Exception e) {
			logger.error("The subtitle " + subtitle + " cannot be persisted");
		} finally {
			session.close();
		}
	}
}
