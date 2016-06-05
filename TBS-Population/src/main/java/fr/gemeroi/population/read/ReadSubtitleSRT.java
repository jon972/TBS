package fr.gemeroi.population.read;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import fr.gemeroi.population.entry.EntryST;
import fr.gemeroi.population.file.SubtitlesFile;
import fr.gemeroi.population.utils.ConvertTypeUtils;

public class ReadSubtitleSRT implements ReadSubtitle {

	private List<EntryST> listEntries = new ArrayList<EntryST>();
	private SubtitlesFile subtitlesFile;
	private String nameSerie;

	public ReadSubtitleSRT(SubtitlesFile subtitleFile) {
		super();
		this.subtitlesFile = subtitleFile;
		read();
	}

	@Override
	public void read() {
		try {
			FileReader fr = new FileReader(subtitlesFile.getFile());
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
							nameSerie, subtitlesFile.getSeasonNumber(), 
							subtitlesFile.getEpisodeNumber(), ++rank));
				}
				line = br.readLine();
			}
		} catch (Exception e) {
			System.out.println(e); // TODO change to a logger
		}
	}

	@Override
	public EntryST getEntry(int entryPos) {
		if (this.listEntries.size() <= entryPos)
			return null;
		return this.listEntries.get(entryPos);
	}

	@Override
	public List<EntryST> getListEntries() {
		return this.listEntries;
	}
}
