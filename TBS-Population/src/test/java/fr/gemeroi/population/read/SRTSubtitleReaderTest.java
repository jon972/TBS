package fr.gemeroi.population.read;


import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;
import org.mockito.Mockito;

import fr.gemeroi.population.file.SubtitlesFile;

public class SRTSubtitleReaderTest {

	public SRTSubtitleReaderTest() {
		
	}

	@Test
	public void read_subtitles_with_one_line() {
		SubtitlesFile subtitleFile = Mockito.mock(SubtitlesFile.class);
		Mockito.when(subtitleFile.getFile()).thenReturn(new File("src/test/resources/subtitlesWithOneLine.srt"));
		SRTSubtitleReader srtSubtitleReader = new SRTSubtitleReader(subtitleFile);
		srtSubtitleReader.read();
		assertEquals(srtSubtitleReader.getEntry(0).getSubtitle(), "Previously on Dexter...");
		assertEquals(srtSubtitleReader.getEntry(1).getSubtitle(), "You really believe in nothing?");
	}

	@Test
	public void read_subtitles_with_several_lines() {
		SubtitlesFile subtitleFile = Mockito.mock(SubtitlesFile.class);
		Mockito.when(subtitleFile.getFile()).thenReturn(new File("src/test/resources/subtitlesWithSeveralLines.srt"));
		SRTSubtitleReader srtSubtitleReader = new SRTSubtitleReader(subtitleFile);
		srtSubtitleReader.read();
		assertEquals(srtSubtitleReader.getEntry(0).getSubtitle(), "Previously on Dexter... Previously on Dexter... Previously on Dexter...");
	}
}
