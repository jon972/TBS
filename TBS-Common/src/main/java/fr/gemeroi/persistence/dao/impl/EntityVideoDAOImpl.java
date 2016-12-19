package fr.gemeroi.persistence.dao.impl;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.inject.Named;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import fr.gemeroi.persistence.bean.Entityvideo;
import fr.gemeroi.persistence.dao.model.EntityVideoDAO;

@ApplicationScoped
public class EntityVideoDAOImpl implements EntityVideoDAO {

//	private static Logger logger = Logger.getLogger(EntityVideoDAOImpl.class);
	private SessionFactory sessionFactory;

//	public EntityVideoDAOImpl(SessionFactory sessionFactory) {
//		this.sessionFactory = sessionFactory;
//	}

	public EntityVideoDAOImpl() {
		
	}

	public List<Entityvideo> getListEntityVideo() {
		Session session = sessionFactory.openSession();

		List<Entityvideo> entityVideos = session.createCriteria(Entityvideo.class).list();
		session.close();
		return entityVideos;
	}

	public Entityvideo getEntityvideo(String entityVideoName, int numSeason, int numEpisode) {
		Session session = sessionFactory.openSession();
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

	public void save(Entityvideo entityvideo) {
		Session session = sessionFactory.openSession();
		try {
			Transaction tx = session.beginTransaction();
			session.save(entityvideo);
			tx.commit();
		} catch (Exception e) {
//			logger.error("The entityvideo " + entityvideo + " cannot be persisted");
		} finally {
			session.close();
		}
	}
}
