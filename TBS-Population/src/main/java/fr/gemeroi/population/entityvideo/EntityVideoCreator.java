package fr.gemeroi.population.entityvideo;

import fr.gemeroi.persistence.bean.Entityvideo;
import fr.gemeroi.persistence.utils.PersistenceUtils;
import fr.gemeroi.population.file.SubtitlesFile;

public class EntityVideoCreator {

	private SubtitlesFile subtitlesFile;
	private String serieName;
	private Entityvideo entityvideo;

	public EntityVideoCreator(SubtitlesFile subtitlesFile, String serieName) {
		this.subtitlesFile = subtitlesFile;
		this.serieName = serieName;
	}

	public void create() {
		EntityVideoBuilder entityVideoBuilder = new EntityVideoBuilder();
		entityvideo = entityVideoBuilder
				.withName(serieName)
				.withNumepisode(subtitlesFile.getEpisodeNumber())
				.withNumsaison(subtitlesFile.getSeasonNumber())
				.build();

		PersistenceUtils.persistObject(entityvideo);
	}

	public Entityvideo getEntityVideo() {
		return this.entityvideo;
	}
}
