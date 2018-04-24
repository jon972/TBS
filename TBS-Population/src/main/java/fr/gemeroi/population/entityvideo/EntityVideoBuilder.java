package fr.gemeroi.population.entityvideo;

import fr.gemeroi.persistence.bean.Entityvideo;

public class EntityVideoBuilder {

	private Entityvideo entityVideo = new Entityvideo();

	public EntityVideoBuilder withName(String name) {
		this.entityVideo.setName(name);
		return this;
	}
	public EntityVideoBuilder withNumepisode(int episode) {
		this.entityVideo.setNumepisode(episode);
		return this;
	}
	public EntityVideoBuilder withNumsaison(int season) {
		this.entityVideo.setNumseason(season);
		return this;
	}
	public EntityVideoBuilder withType(String type) {
		this.entityVideo.setType(type);
		return this;
	}

	public Entityvideo build() {
		return this.entityVideo;
	}
}
