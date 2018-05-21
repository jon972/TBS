package fr.gemeroi.translation.dto;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import fr.gemeroi.persistence.bean.Entityvideo;
import fr.gemeroi.translation.Translation;

public class EntityVideoDTO {

	private Integer id;
	private String name;
	private int numEpisode;
	private int numSeason;
	private String poster;

	public EntityVideoDTO(Integer id, String name, int numEpisode, int numSeason, String poster) {
		this.setName(name);
		this.setNumEpisode(numEpisode);
		this.setNumSeason(numSeason);
		this.setId(id);
		this.setPoster(poster);
	}

	public static EntityVideoDTO valueOf(String json) {
		final GsonBuilder builder = new GsonBuilder();
		final Gson gson = builder.create();
		return gson.fromJson(json, EntityVideoDTO.class);
	}


	public static EntityVideoDTO createEntityVideoDTO(Entityvideo entityvideo) {
		return new EntityVideoDTO(entityvideo.getId(), entityvideo.getName(), 
				entityvideo.getNumepisode(), entityvideo.getNumseason(), entityvideo.getPoster());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumEpisode() {
		return numEpisode;
	}

	public void setNumEpisode(int numEpisode) {
		this.numEpisode = numEpisode;
	}

	public int getNumSeason() {
		return numSeason;
	}

	public void setNumSeason(int numSeason) {
		this.numSeason = numSeason;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + numEpisode;
		result = prime * result + numSeason;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EntityVideoDTO other = (EntityVideoDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (numEpisode != other.numEpisode)
			return false;
		if (numSeason != other.numSeason)
			return false;
		return true;
	}
	
}
