package fr.gemeroi.population.read;

import java.util.*;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.File;

import fr.gemeroi.population.entry.EntryST;
import fr.gemeroi.population.utils.ConvertTypeUtils;

public class ReadSubtitleSRT implements ReadSubtitle {

	private List<EntryST> listEntries = new ArrayList<EntryST>();

	public void read(File subtitleFile, String nameSerie, int saison,
			int episode) {
		readEntries(subtitleFile, nameSerie, saison, episode);
	}

	private void readEntries(File subtitleFile, String nameSerie, int saison,
			int episode) {
		try {
			FileReader fr = new FileReader(subtitleFile);
			BufferedReader br = new BufferedReader(fr);
			String line = br.readLine();
			int rank = 0;
			while (line != null) {
				String entry = "";

				while (line != null && !line.isEmpty()) {
					entry = entry + line + "\n";
					line = br.readLine();
				}

				if (!entry.isEmpty()) {
					listEntries.add(ConvertTypeUtils.convertToEntrySRT(entry,
							nameSerie, saison, episode, ++rank));
				}
				line = br.readLine();
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public EntryST getEntry(int entryPos) {
		if (this.listEntries.size() <= entryPos)
			return null;
		return this.listEntries.get(entryPos);
	}

	public List<EntryST> getListEntries() {
		return this.listEntries;
	}
}
