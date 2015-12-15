package fr.gemeroi.translation;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import fr.gemeroi.common.utils.LanguageEnum;
import fr.gemeroi.persistence.bean.Language;
import fr.gemeroi.persistence.session.SessionMgr;

public class Translate {
	private static String queryHqlFormat = "from %s as l1, %s as l2 where "
										 + "l2.entityvideo.id = l1.entityvideo.id AND "
										 + "l2.timebegin = l1.timebegin AND "
										 + "l1.timebegin > 20 AND "
										 + "(lower(l1.expression) like lower('%% %s %%') "
										 + " OR lower(l1.expression) like lower('%s %%')  "
										 + " OR lower(l1.expression) like lower('%% %s'))";

	public static void main(String[] args) {
		List<Translation> translationList = translate("Hi", LanguageEnum.English, LanguageEnum.French);
		for(Translation translation : translationList) {
			System.out.println(translation);	
		}

	}
	public static List<Translation> translate(String word, LanguageEnum fromLanguage, LanguageEnum toLanguage) {
		SessionFactory sessionFactory = SessionMgr.getSessionFactory();
		Session session = sessionFactory.openSession();
		
		String queryHql = String.format(queryHqlFormat, fromLanguage.getClassLanguage().getCanonicalName(), toLanguage.getClassLanguage().getCanonicalName(), word, word, word);
		Query query = session.createQuery(queryHql);
		query.setMaxResults(30);
		List<Object[]> list = query.list();
		List<Translation> listTranslation = new ArrayList<Translation>();

		for(Object[] resultEntity : list) {
			Language language1 = (Language) resultEntity[0];
			Language language2 = (Language) resultEntity[1];

			listTranslation.add(new Translation(language1.getExpression(), language2.getExpression()));
		}

		return listTranslation;
	}
}
