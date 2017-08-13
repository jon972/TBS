package fr.gemeroi.population;

import fr.gemeroi.common.utils.Language;
import fr.gemeroi.persistence.bean.Entityvideo;
import fr.gemeroi.persistence.bean.Subtitle;

public class SubtitleBuilder {
	private Integer id;
	private Entityvideo entityvideo;
	private String expression;
	private Integer timeBegin;
	private Integer timeEnd;
	private Integer rank;
	private Language language;

	public SubtitleBuilder() {
		
	}

	public SubtitleBuilder withId(Integer id) {
		this.id = id;
		return this;
	}

	public SubtitleBuilder withEntityvideo(Entityvideo entityvideo) {
		this.entityvideo = entityvideo;
		return this;
	}

	public SubtitleBuilder withExpression(String expression) {
		this.expression = expression;
		return this;
	}

	public SubtitleBuilder withTimeBegin(Integer timeBegin) {
		this.timeBegin = timeBegin;
		return this;
	}

	public SubtitleBuilder withTimeEnd(Integer timeEnd) {
		this.timeEnd = timeEnd;
		return this;
	}

	public SubtitleBuilder withRank(Integer rank) {
		this.rank = rank;
		return this;
	}

	public SubtitleBuilder withLanguage(Language language) {
		this.language = language;
		return this;
	}

	public Subtitle build() {
		return new Subtitle(id, entityvideo, expression,
				timeBegin, timeEnd, rank, language.name());
	}
}
