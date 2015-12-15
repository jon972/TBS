package fr.gemeroi.population;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import fr.gemeroi.common.utils.LanguageEnum;
import fr.gemeroi.persistence.bean.Entityvideo;
import fr.gemeroi.persistence.bean.Language;
import fr.gemeroi.persistence.bean.LanguageFactory;
import fr.gemeroi.persistence.session.SessionMgr;
import fr.gemeroi.persistence.utils.query.QueryEntityVideoUtils;
import fr.gemeroi.persistence.utils.query.QueryLanguageUtils;
import fr.gemeroi.population.entry.EntryST;
import fr.gemeroi.population.read.ReadSubtitle;
import fr.gemeroi.population.read.ReadSubtitleSRT;

public class DataFeeder {
	public static void persistSubtitles(File file, String serieName, String videoType, LanguageEnum languageEnum) {
		ReadSubtitle reader = new ReadSubtitleSRT();
		String patternString = "(.*)(\\d+)x(\\d+)(.*)";
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(file.getName());
        matcher.find();

        int episode = Integer.parseInt(matcher.group(3));
        int saison = Integer.parseInt(matcher.group(2));
		System.out.println("Lecture de " + file.getName());
		reader.read(file, "Dexter", saison, episode);
		Entityvideo entityvideo = new Entityvideo(serieName, videoType, saison, episode);
		SessionFactory sessionFactory = SessionMgr.getSessionFactory();
		Session session = sessionFactory.openSession();
		entityvideo = QueryEntityVideoUtils.persistEntityVideo(entityvideo, session);
		List<Language> listLanguage = new ArrayList<>();
		for(EntryST entryST : reader.getListEntries()) {
			Language language = LanguageFactory.getNewLanguage(languageEnum);
			language.setExpression(entryST.getSubtitle());
			language.setRank(entryST.getRank());
			language.setTimebegin(entryST.getDateStart());
			language.setTimeend(entryST.getDateEnd());
			language.setEntityvideo(entityvideo);
			listLanguage.add(language);
		}
		QueryLanguageUtils.sortLanguageByTimeEndSubtitle(listLanguage);
		int lastTimeEndCurrentVideo = listLanguage.get(0).getTimeend();
		System.out.println(lastTimeEndCurrentVideo);

		for(LanguageEnum lEnum : LanguageEnum.values()) {
			List<Language> languageListEntry = QueryLanguageUtils.getListLanguageFromDB(entityvideo, lEnum.getClassLanguage(), session);
			if(languageListEntry != null && languageListEntry.size() > 0) {
				System.out.println(languageListEntry.get(0).getTimeend());
				int endTimeVideoFromAnotherLanguage = languageListEntry.get(0).getTimeend();
				if(endTimeVideoFromAnotherLanguage == lastTimeEndCurrentVideo) {
					QueryLanguageUtils.persistLanguage(listLanguage, session);
					System.out.println("Integration done");
					session.close();
				} else {
					System.out.println("Integration aborted");
				}
				return;
			}
		}
		
		QueryLanguageUtils.persistLanguage(listLanguage, session);
		System.out.println("Integration done");

		session.close();
		
	}
	public static void main(String[] args) throws Exception {

		File f = new File("C:\\Users\\TOSHIBA\\Downloads\\Dexter - season 8.en");
		File f2 = new File("C:\\Users\\TOSHIBA\\Downloads\\Dexter - season 8.fr");

		for(File file : f.listFiles()) {
			persistSubtitles(file, "Dexter", "Serie", LanguageEnum.English);
		}
		
		for(File file : f2.listFiles()) {
			persistSubtitles(file, "Dexter", "Serie", LanguageEnum.French);
		}
//		persistSubtitles(f, "Dexter", "Serie", LanguageEnum.English);
//		persistSubtitles(f2, "Dexter", "Serie",LanguageEnum.French);
	}


}
