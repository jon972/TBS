package fr.gemeroi.translation;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import fr.gemeroi.common.utils.LanguageEnum;
import fr.gemeroi.persistence.bean.Subtitle;
import fr.gemeroi.persistence.bean.User;
import fr.gemeroi.persistence.session.SessionMgr;

public class Translate {
	private static String queryHqlFormat = "from Subtitle as sub1, Subtitle as sub2 where "
										 + "sub1.language = '%s' AND "
										 + "sub2.language = '%s' AND "
										 + "sub2.entityvideo.id = sub1.entityvideo.id AND "
										 + "sub2.timebegin = sub1.timebegin AND "
										 + "sub1.timebegin > 20 AND "
										 + "(lower(sub1.expression) like lower('%% %s %%') "
										 + " OR lower(sub1.expression) like lower('%s %%')  "
										 + " OR lower(sub1.expression) like lower('%% %s'))";

	public static List<Translation> translate(String word, LanguageEnum fromLanguage, LanguageEnum toLanguage, User user) {
		SessionFactory sessionFactory = SessionMgr.getSessionFactory();
		Session session = sessionFactory.openSession();
		
		String queryHql = String.format(queryHqlFormat, fromLanguage.name(), toLanguage.name(), word, word, word);
		Query query = session.createQuery(queryHql);
		query.setMaxResults(30);
		List<Object[]> list = query.list();
		List<Translation> listTranslation = new ArrayList<Translation>();

		for(Object[] resultEntity : list) {
			Subtitle language1 = (Subtitle) resultEntity[0];
			Subtitle language2 = (Subtitle) resultEntity[1];
			
			listTranslation.add(new Translation(language1.getExpression(), language2.getExpression(), false));
				
		}
		session.close();
		return listTranslation;
	}
}
