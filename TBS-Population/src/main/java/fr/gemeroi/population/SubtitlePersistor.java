package fr.gemeroi.population;

import java.io.File;
import java.util.List;

import org.apache.log4j.Logger;

import fr.gemeroi.common.utils.Language;
import fr.gemeroi.persistence.bean.Entityvideo;
import fr.gemeroi.persistence.bean.Subtitle;
import fr.gemeroi.persistence.dao.EntityVideoDAO;
import fr.gemeroi.persistence.dao.SubtitleDAO;
import fr.gemeroi.population.entityvideo.EntityVideoCreator;
import fr.gemeroi.population.extractor.SubtitleExtractor;
import fr.gemeroi.population.file.SubtitlesFile;
import fr.gemeroi.population.reader.SRTSubtitleReader;
import fr.gemeroi.population.reader.SubtitleReader;

public class SubtitlePersistor {

	private static final Logger LOGGER = Logger.getLogger(SubtitlePersistor.class);
	private final String subtitleFileNamePattern;

	public SubtitlePersistor(String subtitleFileNamePattern) {
		this.subtitleFileNamePattern = subtitleFileNamePattern;
	}

	public void persistSubtitles(File subtitleFile, String serieName, Language language) {
		if(subtitleFile.isDirectory()) {
			persistSubtitlesDirectory(subtitleFile, serieName, language);
		} else {
			persistSubtitlesFile(subtitleFile, serieName, language);
		}
	}
	
	private void persistSubtitlesDirectory(File directory, String serieName, Language language) {
		for(File file : directory.listFiles()) {
			persistSubtitlesFile(file, serieName, language);
		}
	}

	private void persistSubtitlesFile(File file, String serieName, Language language) {
		SubtitlesFile subtitlesFile = new SubtitlesFile(file, serieName, language, subtitleFileNamePattern);

		Entityvideo entityvideo = EntityVideoDAO.retrieveEntityvideo(serieName, subtitlesFile.getSeasonNumber(), subtitlesFile.getEpisodeNumber());
		if(!entityVideoAlreadyPersisted(entityvideo)) {
			entityvideo = createEntityVideo(subtitlesFile, serieName);
		}

		SubtitleReader reader = new SRTSubtitleReader(subtitlesFile);
		List<Subtitle> subtitles = new SubtitleExtractor(reader, language, entityvideo).extractSubtitlesFromReader();

		FileMatchValidator fileMatchValidator = new FileMatchValidator(entityvideo, subtitles, language);
		if(fileMatchValidator.isMatchingToOtherSubtitlesPersisted()) {
			SubtitleDAO.persistSubtitles(subtitles, entityvideo, language);
			LOGGER.info("The subtitles from file " + file.getAbsolutePath() + ", the subtitles are persisted");
		} else {
			LOGGER.warn("The subtitles from file " + file.getAbsolutePath() + " doesn't match the one in database, the subtitles were not persisted");
		}
	}

	private Entityvideo createEntityVideo(SubtitlesFile subtitlesFile, String serieName) {
		EntityVideoCreator entityVideoCreator = new EntityVideoCreator(subtitlesFile, serieName);
		entityVideoCreator.create();
		return entityVideoCreator.getEntityVideo();

	}

	private boolean entityVideoAlreadyPersisted(Entityvideo entityvideo) {
		return entityvideo != null;
	}

	public static void main(String[] args) {
		SubtitlePersistor subtitlePersistor = new SubtitlePersistor("(.*)(\\d+)x(\\d+)(.*)");
		subtitlePersistor.persistSubtitles(new File("C:\\Users\\TOSHIBA\\Downloads\\Elementary - season 1.en\\Elementary - 1x07 - One Way to Get Off.HDTV.LOL.en.srt"), "Elementary", Language.ENGLISH);
		subtitlePersistor.persistSubtitles(new File("C:\\Users\\TOSHIBA\\Downloads\\Elementary - season 1.fr\\Elementary - 1x07 - One Way to Get Off.LOL.fr.srt"), "Elementary", Language.FRENCH);
	}
}
