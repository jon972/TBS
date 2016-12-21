package fr.gemeroi.configuration;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import fr.gemeroi.persistence.bean.Entityvideo;
import fr.gemeroi.persistence.bean.Subtitle;
import fr.gemeroi.persistence.bean.User;
import fr.gemeroi.persistence.bean.UsersTranslations;

public class TBSConfigurationForTests implements Configuration {

	@Override
	public SessionFactory getSessionFactory() {
		org.hibernate.cfg.AnnotationConfiguration config = hibernateConfiguration();
		return config.buildSessionFactory();
	}

	@Override
	public AnnotationConfiguration hibernateConfiguration() {
		return new org.hibernate.cfg.AnnotationConfiguration()
		.setProperty("hibernate.connection.driver_class", "org.h2.Driver")
		.setProperty("hibernate.connection.password", "root")
		.setProperty("hibernate.connection.url", "jdbc:h2:mem:test;INIT=RUNSCRIPT FROM 'classpath:databases/test.sql';")
		.setProperty("hibernate.connection.username", "h2")
		.setProperty("hibernate.default_schema", "public")
		.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect")
		.setProperty("show_sql", "true")
		.addAnnotatedClass(Entityvideo.class)
		.addAnnotatedClass(Subtitle.class)
		.addAnnotatedClass(User.class)
		.addAnnotatedClass(UsersTranslations.class);
	}

}
