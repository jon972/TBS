package fr.gemeroi.persistence.utils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import fr.gemeroi.persistence.bean.generated.Entityvideo;
import fr.gemeroi.persistence.session.SessionMgr;

public class QueryEntityVideoUtils {
	public static List<Entityvideo> getListEntityVideo() {
		SessionFactory sessionFactory = SessionMgr.getSessionFactory();
		Session session = sessionFactory.openSession();
		return session.createCriteria(Entityvideo.class).list();
	}

	public static Entityvideo persistEntityVideo(Entityvideo entityvideo, Session session) {
		Entityvideo entityVideoFromDB = getEntityvideo(entityvideo, session);
		if(entityVideoFromDB != null) return entityVideoFromDB;
		try {
			Transaction tx = session.beginTransaction();
			session.save(entityvideo);
			tx.commit();
		} catch (Exception e) {
			return entityvideo;
		}
		return entityvideo;
	}
	
	public static Entityvideo getEntityvideo(Entityvideo entityvideo, Session session) {
		Transaction tx = session.beginTransaction();
		List entityvideoList = 
		 session.createCriteria(Entityvideo.class)
				      .add(Restrictions.eq("nom", entityvideo.getNom()))
				      .add(Restrictions.eq("numsaison", entityvideo.getNumsaison()))
				      .add(Restrictions.eq("numepisode", entityvideo.getNumepisode()))
				      .list();
		if(entityvideoList == null) return null;
		if(entityvideoList.size() < 1) return null;
		return (Entityvideo) entityvideoList.get(0);
	}
}
