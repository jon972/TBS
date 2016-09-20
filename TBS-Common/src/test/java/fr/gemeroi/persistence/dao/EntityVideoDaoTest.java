package fr.gemeroi.persistence.dao;


import static org.junit.Assert.*;

import org.junit.Test;

import fr.gemeroi.persistence.bean.Entityvideo;
import fr.gemeroi.persistence.session.SessionMgr;

public class EntityVideoDaoTest {
	
	@Test
	public void getListEntityVideo_return_list_size_4() {
		assertEquals(EntityVideoDAO.getListEntityVideo().size(), 4);
	}

	@Test
	public void getEntityVideo_if_exist_then_entityVideo () {
		Entityvideo entityVideo = EntityVideoDAO.getEntityvideo("Dexter", 1, 1);
		Entityvideo entityVideoExpected = new Entityvideo("Dexter", "", 1, 1);

		assertEquals(entityVideoExpected, entityVideo);
	}

	@Test
	public void getEntityVideo_if_not_exist_then_null () {
		Entityvideo entityVideo = EntityVideoDAO.getEntityvideo("Entity video does not exist", 1, 1);

		assertEquals(null, entityVideo);
	}

}
