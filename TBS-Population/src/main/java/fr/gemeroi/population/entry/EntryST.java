package fr.gemeroi.population.entry;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class EntryST {

	int startInSec;
	int endInSec;
	String subtitle;
	int rank;
	
	public EntryST(String dateStart, String dateEnd, String subtitle, int rank) {
		this.startInSec = strDateToSec(dateStart);
		this.endInSec = strDateToSec(dateEnd);
		this.subtitle = subtitle;
		this.rank = rank;
	}
	
	public int getDateStart() {
		return this.startInSec;
	}

	public int getDateEnd() {
		return this.endInSec;
	}

	public String getSubtitle() {
		return this.subtitle;
	}

	public int getRank() {
		return this.rank;
	}
	
	private int strDateToSec(String dateStr) {
		Pattern pattern = Pattern.compile("(\\d+):(\\d+):(\\d+),(\\d+)");
		Matcher matcher = pattern.matcher(dateStr);
		matcher.find();
		int hours = Integer.parseInt(matcher.group(1));
		int minutes = Integer.parseInt(matcher.group(2));
		int seconds = Integer.parseInt(matcher.group(3));

		return seconds + minutes * 60 + hours * 3600;
	}
}
