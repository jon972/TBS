package fr.gemeroi.persistence.dao;


import static org.junit.Assert.assertEquals;

import org.junit.Test;

import fr.gemeroi.configuration.Configuration;
import fr.gemeroi.configuration.TBSConfigurationForTests;
import fr.gemeroi.persistence.bean.Entityvideo;
import fr.gemeroi.persistence.dao.impl.EntityVideoDAOImpl;
import fr.gemeroi.persistence.dao.model.EntityVideoDAO;

public class EntityVideoDaoTest {

	private EntityVideoDAO entityVideoDAO;

	public EntityVideoDaoTest() {
		Configuration configuration = new TBSConfigurationForTests();
		entityVideoDAO = new EntityVideoDAOImpl(configuration.getSessionFactory());
	}

	@Test
	public void getListEntityVideo_return_list_size_4() {
		assertEquals(entityVideoDAO.getListEntityVideo().size(), 4);
	}

	@Test
	public void getEntityVideo_if_exist_then_entityVideo () {
		Entityvideo entityVideo = entityVideoDAO.getEntityvideo("Dexter", 1, 1);
		Entityvideo entityVideoExpected = new Entityvideo("Dexter", "", 1, 1);

		assertEquals(entityVideoExpected, entityVideo);
	}

	@Test
	public void getEntityVideo_if_not_exist_then_null () {
		Entityvideo entityVideo = entityVideoDAO.getEntityvideo("Entity video does not exist", 1, 1);

		assertEquals(null, entityVideo);
	}

}
