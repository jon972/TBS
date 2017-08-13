package fr.gemeroi.population.extractor;

import java.util.ArrayList;
import java.util.List;

import fr.gemeroi.common.utils.Language;
import fr.gemeroi.persistence.bean.Entityvideo;
import fr.gemeroi.persistence.bean.Subtitle;
import fr.gemeroi.population.SubtitleBuilder;
import fr.gemeroi.population.entry.EntryST;
import fr.gemeroi.population.reader.SubtitleReader;

public class SubtitleExtractor {

	private SubtitleReader reader;
	private Language language;
	private Entityvideo entityvideo;
	
	public SubtitleExtractor(SubtitleReader reader, Language language, Entityvideo entityvideo) {
		this.reader = reader;
		this.language = language;
		this.entityvideo = entityvideo;
	}

	public List<Subtitle> extractSubtitlesFromReader() {
		List<Subtitle> subtitles = new ArrayList<>();
		for(EntryST entryST : reader.getListEntries()) {
			SubtitleBuilder subtitleBuilder = new SubtitleBuilder();
			Subtitle subtitle = subtitleBuilder.withExpression(entryST.getSubtitle())
							.withRank(entryST.getRank())
							.withTimeBegin(entryST.getDateStart())
							.withTimeEnd(entryST.getDateEnd())
							.withEntityvideo(entityvideo)
							.withLanguage(language)
							.build();
			subtitles.add(subtitle);
		}

		return subtitles;
	}
}
