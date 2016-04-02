package fr.gemeroi.persistence.session;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import fr.gemeroi.persistence.bean.Subtitle;
import fr.gemeroi.persistence.bean.Entityvideo;
import fr.gemeroi.persistence.bean.User;
import fr.gemeroi.persistence.bean.UsersTranslations;


public class SessionMgr {
	private static SessionFactory factory;
	
	private SessionMgr() {
	}
	
	public static SessionFactory getSessionFactory() {
		if(factory == null) {
			factory = new AnnotationConfiguration().
	                   configure().
	                   addPackage("fr.gemeroi.population.persistence.bean.generated").
	                   addAnnotatedClass(Entityvideo.class).
	                   addAnnotatedClass(Subtitle.class).
	                   addAnnotatedClass(User.class).
	                   addAnnotatedClass(UsersTranslations.class).
	                   buildSessionFactory();
		}
		return factory;
	}
}
