package fr.gemeroi.population;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
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
	private static final Logger logger = Logger.getLogger(DataFeeder.class);

	private static final String patternSeasonEpisode = "(.*)(\\d+)x(\\d+)(.*)";
	private static SessionFactory sessionFactory = SessionMgr.getSessionFactory();
	private static Session session;

	private static int getEpisodeNumberFromFileName(String fileName) {
		Pattern pattern = Pattern.compile(patternSeasonEpisode);
        Matcher matcher = pattern.matcher(fileName);
        matcher.find();

        return Integer.parseInt(matcher.group(3));
	}

	private static int getSeasonNumberFromFileName(String fileName) {
		Pattern pattern = Pattern.compile(patternSeasonEpisode);
        Matcher matcher = pattern.matcher(fileName);
        matcher.find();

        return Integer.parseInt(matcher.group(2));
	}

	private static List<Language> convertSubtitlesToLanguageSubtilesForIntegration(ReadSubtitle reader, LanguageEnum languageEnum, Entityvideo entityvideoPersisted) {
		List<Language> listLanguage = new ArrayList<>();
		for(EntryST entryST : reader.getListEntries()) {
			Language language = LanguageFactory.getNewLanguage(languageEnum);
			language.setExpression(entryST.getSubtitle());
			language.setRank(entryST.getRank());
			language.setTimebegin(entryST.getDateStart());
			language.setTimeend(entryST.getDateEnd());
			language.setEntityvideo(entityvideoPersisted);
			listLanguage.add(language);
		}

		return listLanguage;
	}

	private static void persistLanguageSubtitlesIfCorrect(Entityvideo entityvideoFromDB, List<Language> listLanguage, int lastTimeEndCurrentVideo) {
		for(LanguageEnum lEnum : LanguageEnum.values()) {
			List<Language> languageListEntry = QueryLanguageUtils.getListLanguageFromDB(entityvideoFromDB, lEnum.getClassLanguage(), session);
			if(languageListEntry != null && languageListEntry.size() > 0) {
				int endTimeVideoFromAnotherLanguage = languageListEntry.get(0).getTimeend();
				if(endTimeVideoFromAnotherLanguage == lastTimeEndCurrentVideo) {
					QueryLanguageUtils.persistLanguage(listLanguage, session);
					logger.info("Integration done");
					session.close();
				} else {
					logger.error("Integration aborted");
				}
				return;
			}
		}
		
		QueryLanguageUtils.persistLanguage(listLanguage, session);
		logger.info("Integration done");

		session.close();
	}

	public static void persistSubtitles(File file, String serieName, String videoType, LanguageEnum languageEnum) {
		ReadSubtitle reader = new ReadSubtitleSRT();

        int episode = getEpisodeNumberFromFileName(file.getName());
        int season = getSeasonNumberFromFileName(file.getName());
        
        logger.info("Reading file : " + file.getName());
		reader.read(file, serieName, season, episode);

		Entityvideo entityvideo = new Entityvideo(serieName, videoType, season, episode);
		session = sessionFactory.openSession();
		Entityvideo entityvideoFromDB = QueryEntityVideoUtils.persistEntityVideo(entityvideo, session);

		List<Language> listLanguage = convertSubtitlesToLanguageSubtilesForIntegration(reader, languageEnum, entityvideoFromDB);
		QueryLanguageUtils.sortLanguageByTimeEndSubtitle(listLanguage);
		int lastTimeEndCurrentVideo = listLanguage.get(0).getTimeend();

		persistLanguageSubtitlesIfCorrect(entityvideoFromDB, listLanguage, lastTimeEndCurrentVideo);
		
	}
}
