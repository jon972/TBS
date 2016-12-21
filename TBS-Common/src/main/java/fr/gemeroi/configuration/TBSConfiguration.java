package fr.gemeroi.configuration;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import fr.gemeroi.persistence.bean.Entityvideo;
import fr.gemeroi.persistence.bean.Subtitle;
import fr.gemeroi.persistence.bean.User;
import fr.gemeroi.persistence.bean.UsersTranslations;

public class TBSConfiguration implements Configuration {

	@Override
	public AnnotationConfiguration hibernateConfiguration() {
		return new org.hibernate.cfg.AnnotationConfiguration()
			.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver")
			.setProperty("hibernate.connection.password", "root")
			.setProperty("hibernate.connection.url", "jdbc:postgresql://localhost/Translation2")
			.setProperty("hibernate.connection.username", "postgres")
			.setProperty("hibernate.default_schema", "public")
			.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect")
			.setProperty("show_sql", "true")
			.addAnnotatedClass(Entityvideo.class)
			.addAnnotatedClass(Subtitle.class)
			.addAnnotatedClass(User.class)
			.addAnnotatedClass(UsersTranslations.class);
	}

	@Override
	public SessionFactory getSessionFactory() {
		org.hibernate.cfg.AnnotationConfiguration config = hibernateConfiguration();
		return config.buildSessionFactory();
	}

}
