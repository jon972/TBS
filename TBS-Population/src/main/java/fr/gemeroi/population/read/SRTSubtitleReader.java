package fr.gemeroi.population.read;

import java.io.BufferedReader;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import fr.gemeroi.population.entry.EntryST;
import fr.gemeroi.population.file.SubtitlesFile;
import fr.gemeroi.population.utils.ConvertTypeUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SRTSubtitleReader implements SubtitleReader {

	final static Logger logger = LoggerFactory.getLogger(SRTSubtitleReader.class);

	private List<EntryST> listEntries = new ArrayList<EntryST>();
	private SubtitlesFile subtitlesFile;
	private String nameSerie;

	public SRTSubtitleReader(SubtitlesFile subtitleFile) {
		super();
		this.subtitlesFile = subtitleFile;
		read();
	}

	@Override
	public void read() {
		try {
			BufferedReader br = 
					Files.newBufferedReader(FileSystems.getDefault().getPath(subtitlesFile.getFile().getAbsolutePath()), Charset.forName("UTF-8"));
			int rank = 0;
			String line = br.readLine();
			while(line != null) {
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
			logger.error("Problem in reading SRT file");
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
