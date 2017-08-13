package fr.gemeroi.population.reader;

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

	static final Logger LOGGER = LoggerFactory.getLogger(SRTSubtitleReader.class);

	private List<EntryST> listEntries = new ArrayList<>();
	private SubtitlesFile subtitlesFile;

	public SRTSubtitleReader(SubtitlesFile subtitleFile) {
		super();
		this.subtitlesFile = subtitleFile;
		read();
	}

	@Override
	public void read() {
		try(BufferedReader br = 
					Files.newBufferedReader(FileSystems.getDefault().getPath(subtitlesFile.getFile().getAbsolutePath()), Charset.forName("UTF-8"));
			) {
			int rank = 0;
			String line = br.readLine();
			while(line != null) {
				StringBuilder entry = new StringBuilder();

				while (line != null && !line.isEmpty()) {
					entry.append(line + "\n");
					line = br.readLine();
				}

				if (entry.length() != 0) {
					listEntries.add(ConvertTypeUtils.convertToEntrySRT(new String(entry), ++rank));
				}
				line = br.readLine();
			}
		} catch (Exception e) {
			LOGGER.error("Problem in reading SRT file : " + subtitlesFile.getFile().getName());
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
