package fr.gemeroi.population;

import java.util.Collections;
import java.util.List;

import fr.gemeroi.common.utils.Language;
import fr.gemeroi.persistence.bean.Entityvideo;
import fr.gemeroi.persistence.bean.Subtitle;
import fr.gemeroi.persistence.dao.SubtitleDAO;
import fr.gemeroi.population.utils.SubtitleComparator;

public class FileMatchValidator {

	private Entityvideo entityvideo;
	private List<Subtitle> subtitles;
	private Language language;
	
	public FileMatchValidator(Entityvideo entityvideo, List<Subtitle> subtitles, Language language) {
		this.entityvideo = entityvideo;
		this.subtitles = subtitles;
		this.language = language;
	}

	public boolean isMatchingToOtherSubtitlesPersisted() {
		Collections.sort(subtitles, new SubtitleComparator());
		if(subtitles.isEmpty()) return false;
		int lastTimeEndCurrentVideo = subtitles.get(0).getTimeend();

		List<Subtitle> languageEntries = SubtitleDAO.retrieveSubtitles(entityvideo, language);
		if(languageEntries == null || languageEntries.isEmpty()) {
			return true;
		} else {
			int endTimeVideoFromAnotherLanguage = languageEntries.get(0).getTimeend();
			return endTimeVideoFromAnotherLanguage == lastTimeEndCurrentVideo;
		}
	}
}
