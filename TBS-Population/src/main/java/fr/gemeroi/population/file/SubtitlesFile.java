package fr.gemeroi.population.file;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fr.gemeroi.common.utils.Language;

public class SubtitlesFile {
	private Language language;
	private String patternSeasonEpisode;
	private File file;
	private int seasonNumber;
	private int episodeNumber;
	private String nameEntityvideo;

	public SubtitlesFile(File file, String nameEntityvideo, Language language, String patternSeasonEpisode) {
		this.file = file;
		this.language = language;
		this.patternSeasonEpisode = patternSeasonEpisode;
		this.seasonNumber = this.parseSeasonNumer();
		this.episodeNumber = this.parseEpisodeNumber();
		this.setNameEntityvideo(nameEntityvideo);
	}

	public int getEpisodeNumber() {
		return this.episodeNumber;
	}

	public int getSeasonNumber() {
		return this.seasonNumber;
	}

	public File getFile() {
		return file;
	}

	public Language getLanguage() {
		return language;
	}

	public String getNameEntityvideo() {
		return nameEntityvideo;
	}

	public void setNameEntityvideo(String nameEntityvideo) {
		this.nameEntityvideo = nameEntityvideo;
	}

	private int parseSeasonNumer() {
		Pattern pattern = Pattern.compile(patternSeasonEpisode);
		Matcher matcher = pattern.matcher(this.file.getName());
		matcher.find();

		return Integer.parseInt(matcher.group(2));
	}

	private int parseEpisodeNumber() {
		Pattern pattern = Pattern.compile(patternSeasonEpisode);
		Matcher matcher = pattern.matcher(this.file.getName());
		matcher.find();

		return Integer.parseInt(matcher.group(3));
	}
}
