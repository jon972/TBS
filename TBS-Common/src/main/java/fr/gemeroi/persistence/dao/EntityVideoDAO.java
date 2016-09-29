package fr.gemeroi.persistence.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import fr.gemeroi.persistence.bean.Entityvideo;
import fr.gemeroi.persistence.session.SessionMgr;

public class EntityVideoDAO {
	public static List<Entityvideo> getListEntityVideo() {
		SessionFactory sessionFactory = SessionMgr.getSessionFactory();
		Session session = sessionFactory.openSession();

		List<Entityvideo> entityVideos = session.createCriteria(Entityvideo.class).list();
		session.close();
		return entityVideos;
	}

	public static Entityvideo getEntityvideo(String entityVideoName, int numSeason, int numEpisode) {
		Session session = SessionMgr.getSessionFactory().openSession();
		List<Entityvideo> entityvideoList = 
		 session.createCriteria(Entityvideo.class)
				      .add(Restrictions.eq("nom", entityVideoName))
				      .add(Restrictions.eq("numsaison", numSeason))
				      .add(Restrictions.eq("numepisode", numEpisode))
				      .list();
		if(entityvideoList == null) return null;
		if(entityvideoList.size() < 1) return null;
		Entityvideo entityvideo = entityvideoList.get(0);
		session.close();
		return entityvideo;
	}
}
