package fr.gemeroi.configuration;

import org.hibernate.SessionFactory;

import fr.gemeroi.persistence.bean.Entityvideo;
import fr.gemeroi.persistence.bean.Subtitle;
import fr.gemeroi.persistence.bean.User;
import fr.gemeroi.persistence.bean.UsersTranslations;
import fr.gemeroi.persistence.dao.impl.EntityVideoDAOImpl;
import fr.gemeroi.persistence.dao.impl.SubtitleDAOImpl;
import fr.gemeroi.persistence.dao.impl.UsersDAOImpl;
import fr.gemeroi.persistence.dao.impl.UsersTranslationsDAOImpl;

//@Configuration
public class TBSConfiguration {

//	@Bean
//	public SessionFactory sessionFactory() {
//		org.hibernate.cfg.AnnotationConfiguration config = this.hibernateConfiguration();
//		return config.buildSessionFactory();
//	}
//
//	@Bean
//	public EntityVideoDAO entityVideoDAO() {
//		SessionFactory sessionFactory = sessionFactory();
//		return new EntityVideoDAO(sessionFactory);
//	}
//
//	@Bean
//	public SubtitleDAO subtitleDAO() {
//		SessionFactory sessionFactory = sessionFactory();
//		return new SubtitleDAO(sessionFactory);
//	}
//
//	@Bean
//	public UsersDAO usersDAO() {
//		SessionFactory sessionFactory = sessionFactory();
//		return new UsersDAO(sessionFactory);
//	}
//
//	@Bean
//	public UsersTranslationsDAO usersTranslations() {
//		SessionFactory sessionFactory = sessionFactory();
//		return new UsersTranslationsDAO(sessionFactory);
//	}
//
//	@Bean
//	public org.hibernate.cfg.AnnotationConfiguration hibernateConfiguration() {
//		return new org.hibernate.cfg.AnnotationConfiguration()
//				.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver")
//				.setProperty("hibernate.connection.password", "root")
//				.setProperty("hibernate.connection.url", "jdbc:postgresql://localhost/Translation2")
//				.setProperty("hibernate.connection.username", "postgres")
//				.setProperty("hibernate.default_schema", "public")
//				.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect")
//				.setProperty("show_sql", "true")
//				.addAnnotatedClass(Entityvideo.class)
//				.addAnnotatedClass(Subtitle.class)
//				.addAnnotatedClass(User.class)
//				.addAnnotatedClass(UsersTranslations.class);
//	}
}
