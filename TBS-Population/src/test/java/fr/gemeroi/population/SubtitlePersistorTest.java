package fr.gemeroi.population;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.List;

import org.junit.Test;

import fr.gemeroi.common.utils.Language;
import fr.gemeroi.configuration.Configuration;
import fr.gemeroi.configuration.TBSConfigurationForTests;
import fr.gemeroi.persistence.bean.Entityvideo;
import fr.gemeroi.persistence.dao.impl.EntityVideoDAOImpl;
import fr.gemeroi.persistence.dao.model.EntityVideoDAO;

public class SubtitlePersistorTest {

	private EntityVideoDAO entityVideoDAO;
	private Configuration configuration;

	public SubtitlePersistorTest() {
		configuration = new TBSConfigurationForTests();
		entityVideoDAO = new EntityVideoDAOImpl(configuration.getSessionFactory());
	}

	@Test
	public void if_persist_subtitle_file_then_DB_contains_one_entityVideo() {
		SubtitlePersistor subtitlePersistor= new SubtitlePersistor("(.*)(\\d+)x(\\d+)(.*)", configuration);
		subtitlePersistor.persistSubtitles(new File("src/test/resources/Dexter 01x01.srt"), "Dexter", Language.English);

		List<Entityvideo> entityvideos = entityVideoDAO.getListEntityVideo();
		assertTrue(entityvideos.size() == 1);
	}

//	@Test
//	public void if_persist_subtitle_file_then_DB_contains_subtitles() {
//		
//	}
}
