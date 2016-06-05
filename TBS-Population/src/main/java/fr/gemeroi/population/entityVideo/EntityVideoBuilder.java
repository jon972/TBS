package fr.gemeroi.population.entityVideo;

import fr.gemeroi.persistence.bean.Entityvideo;

public class EntityVideoBuilder {

	private Entityvideo entityVideo = new Entityvideo();

	public EntityVideoBuilder addNom(String name) {
		this.entityVideo.setNom(name);
		return this;
	}
	public EntityVideoBuilder addNumepisode(int episode) {
		this.entityVideo.setNumepisode(episode);
		return this;
	}
	public EntityVideoBuilder addNumsaison(int season) {
		this.entityVideo.setNumsaison(season);
		return this;
	}
	public EntityVideoBuilder addType(String type) {
		this.entityVideo.setType(type);
		return this;
	}

	public Entityvideo build() {
		return this.entityVideo;
	}
}
