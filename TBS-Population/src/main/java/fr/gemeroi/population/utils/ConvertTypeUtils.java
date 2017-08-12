package fr.gemeroi.population.utils;

import fr.gemeroi.population.entry.EntrySRT;

public class ConvertTypeUtils {

	private ConvertTypeUtils() {}

	 /* We parse subtitles with this format
	  00:02:48,414 --> 00:02:51,882
   	  Now she sees me as just a killer.
	 */
	public static EntrySRT convertToEntrySRT(String entryStr, int rank) {
		String[] entryStrSplitted = entryStr.split("\n");

		String[] splitStartEndDates = entryStrSplitted[1].split("-->");

		String dateStart = splitStartEndDates[0];
		String dateEnd = splitStartEndDates[1];

		String subtitle = getTheRestAsSubtitle(entryStrSplitted);

		return new EntrySRT(rank, dateStart, dateEnd, subtitle);
	}

	private static String getTheRestAsSubtitle(String[] subtitle) {
		StringBuilder subtitleBuilder = new StringBuilder(subtitle[2]);
		int subLineNb = 3;
		while (subLineNb < subtitle.length) {
			subtitleBuilder.append(" " + subtitle[subLineNb]);
			subLineNb++;
		}

		return new String(subtitleBuilder);
	}

}
