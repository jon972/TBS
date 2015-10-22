package fr.gemeroi.population.utils;

import fr.gemeroi.population.entry.EntrySRT;

public class ConvertTypeUtils {
	
	 /* On parse les sous titres au format suivant
	  00:02:48,414 --> 00:02:51,882
   	  Now she sees me as just a killer.
	 */
	public static EntrySRT convertToEntrySRT(String entryStr, String nameSerie,
			int season, int episode, int rank) {
		String[] entryStrSplitted = entryStr.split("\n");

		String[] splitStartEndDates = entryStrSplitted[1].split("-->");

		String dateStart = splitStartEndDates[0];
		String dateEnd = splitStartEndDates[1];

		String subtitle = getTheRestAsSubtitle(entryStrSplitted);

		return new EntrySRT(rank, dateStart, dateEnd, subtitle);
	}

	private static String getTheRestAsSubtitle(String[] subtitle) {
		String subtitleConcat = subtitle[2];
		int i = 3;
		while (i < subtitle.length) {
			subtitleConcat += " " + subtitle[i];
			i++;
		}

		return subtitleConcat;
	}

}
