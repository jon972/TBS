package fr.gemeroi.persistence.utils.query;

import org.hibernate.Session;

import fr.gemeroi.persistence.bean.Subtitle;
import fr.gemeroi.persistence.session.SessionMgr;

public class QuerySubtitleUtils {

	public static Subtitle getSubtitleById(Integer id) {
		Session session = SessionMgr.getSessionFactory().openSession();
		Subtitle subtitle = (Subtitle) session.get(Subtitle.class, id);
		session.close();
		return subtitle;
	}
}
