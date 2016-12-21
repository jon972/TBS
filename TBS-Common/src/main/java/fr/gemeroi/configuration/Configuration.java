package fr.gemeroi.configuration;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public interface Configuration {

	public SessionFactory getSessionFactory();
	public AnnotationConfiguration hibernateConfiguration();
}
