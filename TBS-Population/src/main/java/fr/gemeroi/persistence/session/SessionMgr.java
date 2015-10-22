package fr.gemeroi.persistence.session;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import fr.gemeroi.persistence.bean.generated.English;
import fr.gemeroi.persistence.bean.generated.Entityvideo;
import fr.gemeroi.persistence.bean.generated.French;


public class SessionMgr {
	private static SessionFactory factory;
	
	private SessionMgr() {
	}
	
	public static SessionFactory getSessionFactory() {
		if(factory == null) {
			factory = new AnnotationConfiguration().
	                   configure().
	                   addPackage("fr.gemeroi.population.persistence.bean.generated"). //add package if used.
	                   addAnnotatedClass(Entityvideo.class).
	                   addAnnotatedClass(French.class).
	                   addAnnotatedClass(English.class).
	                   buildSessionFactory();
		}
		return factory;
	}
}
