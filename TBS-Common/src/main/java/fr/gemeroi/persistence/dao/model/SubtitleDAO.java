package fr.gemeroi.persistence.dao.model;

import java.util.List;

import fr.gemeroi.common.utils.Language;
import fr.gemeroi.persistence.bean.Entityvideo;
import fr.gemeroi.persistence.bean.Subtitle;
import fr.gemeroi.persistence.dao.impl.SubtitleDAOImpl;

public interface SubtitleDAO {

	public Subtitle getSubtitleById(Integer id);
	public boolean expressionAlreadyExistForTheCurrentVideo(Entityvideo entityvideo, Language languageEnum);
	public List<Subtitle> getSubtitlesFromDB(Entityvideo entityvideo, Language languageEnum);
	public void persistSubtitles(List<Subtitle> subtitles, Entityvideo entityvideo, Language languageEnum);
	public void save(SubtitleDAOImpl subtitleDAO);
}
