package fr.gemeroi.population;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import fr.gemeroi.common.utils.Language;
import fr.gemeroi.configuration.Configuration;
import fr.gemeroi.configuration.TBSConfiguration;
import fr.gemeroi.persistence.bean.Entityvideo;
import fr.gemeroi.persistence.bean.Subtitle;
import fr.gemeroi.persistence.dao.model.EntityVideoDAO;
import fr.gemeroi.persistence.dao.impl.EntityVideoDAOImpl;
import fr.gemeroi.persistence.dao.impl.SubtitleDAOImpl;
import fr.gemeroi.persistence.dao.model.SubtitleDAO;
import fr.gemeroi.population.entityVideo.EntityVideoBuilder;
import fr.gemeroi.population.entry.EntryST;
import fr.gemeroi.population.file.SubtitlesFile;
import fr.gemeroi.population.read.SubtitleReader;
import fr.gemeroi.population.read.SRTSubtitleReader;

public class SubtitlePersistor {

	private final String subtitleFileNamePattern;
	private final SubtitleDAO subtitleDAO;
	private final EntityVideoDAO entityVideoDAO;

	public SubtitlePersistor(String subtitleFileNamePattern, Configuration configuration) {
		this.subtitleFileNamePattern = subtitleFileNamePattern;
		this.subtitleDAO = new SubtitleDAOImpl(configuration.getSessionFactory());
		this.entityVideoDAO = new EntityVideoDAOImpl(configuration.getSessionFactory());
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
		Entityvideo entityvideo = createEntityVideo(subtitlesFile, serieName);
		SubtitleReader reader = new SRTSubtitleReader(subtitlesFile);
		List<Subtitle> subtitles = buildSubtitles(reader, language, entityvideo);

		if(isMatchingOtherPersistedSubtitles(entityvideo, subtitles, language)) {
			subtitleDAO.persistSubtitles(subtitles, entityvideo, language);
		}
	}

	private boolean isMatchingOtherPersistedSubtitles(Entityvideo entityvideo, List<Subtitle> subtitles, Language language) {
		sortSubtitlesByTimeEnd(subtitles);
		if(subtitles.isEmpty()) return false;
		int lastTimeEndCurrentVideo = subtitles.get(0).getTimeend();

		List<Subtitle> languageEntries = subtitleDAO.getSubtitlesFromDB(entityvideo, language);
		if(languageEntries != null && languageEntries.size() > 0) {
			int endTimeVideoFromAnotherLanguage = languageEntries.get(0).getTimeend();
			return endTimeVideoFromAnotherLanguage == lastTimeEndCurrentVideo;
		}

		return true;
	}

	private Entityvideo createEntityVideo(SubtitlesFile subtitlesFile, String serieName) {
		Entityvideo entityvideo = entityVideoDAO.getEntityvideo(serieName, subtitlesFile.getSeasonNumber(), subtitlesFile.getEpisodeNumber());
		if(entityvideo == null) {
			EntityVideoBuilder entityVideoBuilder = new EntityVideoBuilder();
			entityvideo = entityVideoBuilder
					.addNom(serieName)
					.addNumepisode(subtitlesFile.getEpisodeNumber())
					.addNumsaison(subtitlesFile.getSeasonNumber())
					.build();

			entityVideoDAO.save(entityvideo);
		}

		return entityvideo;
	}

	private void sortSubtitlesByTimeEnd(List<Subtitle> subtitles) {
		Collections.sort(subtitles, new Comparator<Subtitle>() {
			public int compare(Subtitle sub1, Subtitle sub2) {
				if (sub1.getTimeend() > sub2.getTimeend())
					return -1;
				if (sub1.getTimeend() < sub2.getTimeend())
					return 1;
				return 0;
			}
		});
	}

	private List<Subtitle> buildSubtitles(SubtitleReader reader, Language language, Entityvideo entityvideo) {
		List<Subtitle> subtitles = new ArrayList<>();
		for(EntryST entryST : reader.getListEntries()) {
			Subtitle subtitle = new Subtitle();
			subtitle.setExpression(entryST.getSubtitle());
			subtitle.setRank(entryST.getRank());
			subtitle.setTimebegin(entryST.getDateStart());
			subtitle.setTimeend(entryST.getDateEnd());
			subtitle.setEntityvideo(entityvideo);
			subtitle.setLanguage(language.name());
			subtitles.add(subtitle);
		}

		return subtitles;
	}

	public static void main(String[] args) {
		Configuration configuration = new TBSConfiguration();
		SubtitlePersistor subtitlePersistor = new SubtitlePersistor("(.*)(\\d+)x(\\d+)(.*)", configuration);
		subtitlePersistor.persistSubtitles(new File("C:\\Users\\TOSHIBA\\Downloads\\Elementary - season 1.en\\Elementary - 1x07 - One Way to Get Off.HDTV.LOL.en.srt"), "Elementary", Language.English);
		subtitlePersistor.persistSubtitles(new File("C:\\Users\\TOSHIBA\\Downloads\\Elementary - season 1.fr\\Elementary - 1x07 - One Way to Get Off.LOL.fr.srt"), "Elementary", Language.French);
	}
}
