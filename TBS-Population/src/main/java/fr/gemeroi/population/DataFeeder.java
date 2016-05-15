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
import fr.gemeroi.persistence.bean.Subtitle;
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

	private static List<Subtitle> buildSubtitles(ReadSubtitle reader, LanguageEnum languageEnum, Entityvideo entityvideoPersisted) {
		List<Subtitle> subtitles = new ArrayList<>();
		for(EntryST entryST : reader.getListEntries()) {
			Subtitle subtitle = new Subtitle();
			subtitle.setExpression(entryST.getSubtitle());
			subtitle.setRank(entryST.getRank());
			subtitle.setTimebegin(entryST.getDateStart());
			subtitle.setTimeend(entryST.getDateEnd());
			subtitle.setEntityvideo(entityvideoPersisted);
			subtitle.setLanguage(languageEnum.name());
			subtitles.add(subtitle);
		}

		return subtitles;
	}

	private static void persistSubtitlesIfCorrect(Entityvideo entityvideoFromDB, List<Subtitle> subtitles, int lastTimeEndCurrentVideo, LanguageEnum languageEnum) {
		List<Subtitle> languageListEntry = QueryLanguageUtils.getSubtitlesFromDB(entityvideoFromDB, session, languageEnum);
		if(languageListEntry != null && languageListEntry.size() > 0) {
			int endTimeVideoFromAnotherLanguage = languageListEntry.get(0).getTimeend();
			if(endTimeVideoFromAnotherLanguage == lastTimeEndCurrentVideo) {
				QueryLanguageUtils.persistSubtitles(subtitles, session, entityvideoFromDB, languageEnum);
				logger.info("Integration done");
				session.close();
			} else {
				logger.error("Integration aborted");
			}
			return;
		}

		QueryLanguageUtils.persistSubtitles(subtitles, session, entityvideoFromDB, languageEnum);
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

		List<Subtitle> subtitles = buildSubtitles(reader, languageEnum, entityvideoFromDB);
		QueryLanguageUtils.sortSubtitlesByTimeEnd(subtitles);
		int lastTimeEndCurrentVideo = subtitles.get(0).getTimeend();

		persistSubtitlesIfCorrect(entityvideoFromDB, subtitles, lastTimeEndCurrentVideo, languageEnum);
		
	}

	public static void main(String[] args) {
		persistSubtitles(new File("C:\\Users\\TOSHIBA\\Downloads\\Dexter - season 7.fr\\Dexter - 7x07 - Chemistry.fr.srt"), "Dexter", "Serie", LanguageEnum.French);
		persistSubtitles(new File("C:\\Users\\TOSHIBA\\Downloads\\Dexter - season 7.en\\Dexter - 7x07 - Chemistry.HDTV.ASAP.en.srt"), "Dexter", "Serie", LanguageEnum.English);
	}
}
