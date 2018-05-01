package fr.gemeroi.translation.dto;

public class EntityVideoDTO {

	private String name;
	private int numEpisode;
	private int numSeason;

	public EntityVideoDTO(String name, int numEpisode, int numSeason) {
		this.setName(name);
		this.setNumEpisode(numEpisode);
		this.setNumSeason(numSeason);
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
	
}
