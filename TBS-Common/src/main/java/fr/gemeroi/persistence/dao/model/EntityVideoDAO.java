package fr.gemeroi.persistence.dao.model;

import java.util.List;

import fr.gemeroi.persistence.bean.Entityvideo;

public interface EntityVideoDAO {

	public List<Entityvideo> getListEntityVideo();
	public Entityvideo getEntityvideo(String entityVideoName, int numSeason, int numEpisode);
	public void save(Entityvideo entityvideo);
}
