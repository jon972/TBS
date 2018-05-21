package fr.gemeroi.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.sun.jersey.multipart.FormDataParam;

import fr.gemeroi.common.utils.Language;
import fr.gemeroi.persistence.bean.Entityvideo;
import fr.gemeroi.persistence.bean.Subtitle;
import fr.gemeroi.persistence.bean.User;
import fr.gemeroi.persistence.bean.UsersPersonalTranslations;
import fr.gemeroi.persistence.bean.UsersTranslations;
import fr.gemeroi.persistence.dao.SubtitleDAO;
import fr.gemeroi.persistence.dao.TranslationDAO;
import fr.gemeroi.persistence.dao.UsersPersonalTranslationsDao;
import fr.gemeroi.persistence.dao.UsersTranslationsDAO;
import fr.gemeroi.persistence.utils.PersistenceUtils;
import static fr.gemeroi.services.reponses.HeadersResponses.ACCESS_CONTROL_ALLOW_ORIGIN;
import fr.gemeroi.services.reponses.Responses;
import fr.gemeroi.translation.Translation;
import fr.gemeroi.translation.dto.EntityVideoDTO;
import fr.gemeroi.user.creation.UsersCache;
import fr.gemeroi.user.translation.UserTranslationsMgr;

@Path("/translation")
public class TranslationServices {

	private static final Logger LOGGER = Logger.getLogger(TranslationServices.class);

	@Path("/{word}/{language1}/{language2}")
	@GET
	@Produces("application/json")
	public Response translate(@PathParam("word") String wordToTranslate, @PathParam("language1") Language language1,
			@PathParam("language2") Language language2, @HeaderParam("token") String token) {
		User user = UsersCache.getInstance().getUser(token);
		Set<Translation> wordTranslatedList = TranslationDAO.translate(wordToTranslate, language1, language2, user);

		return Responses.responseOk(wordTranslatedList);
	}

	@Path("saveTranslation")
	@POST
	@Produces("application/json")
	@Consumes("application/json")
	public Response saveTranslation(@HeaderParam("token") String token, @HeaderParam("translation") Translation trans) {

		User user = UsersCache.getInstance().getUser(token);
		UsersTranslations usersTranslations = new UsersTranslations(user.getEmail(),
				SubtitleDAO.getSubtitleById(trans.getSubtitleDTOToTranslate().getId()),
				SubtitleDAO.getSubtitleById(trans.getSubtitleDTOTranslated().getId()));
		PersistenceUtils.persistObject(usersTranslations);

		return Response.ok().header(ACCESS_CONTROL_ALLOW_ORIGIN, "*").build();
	}

	@Path("retrieveMyTranslations")
	@POST
	@Produces("application/json")
	public Response retrieveMyTranslations(@HeaderParam("token") String token,
			@HeaderParam("languageFrom") Language languageFrom, @HeaderParam("languageTo") Language languageTo) {

		User user = UsersCache.getInstance().getUser(token);

		List<UsersTranslations> usersTranslations = UsersTranslationsDAO.retrieveUsersTranslations(user.getEmail(),
				languageFrom, languageTo);
		List<Translation> translations = UserTranslationsMgr.convertUsersTranslationsToTranslation(usersTranslations);
		translations.addAll(TranslationDAO.getUsersPersonalTranslation(user, languageFrom.name(), languageTo.name()));
		return Responses.responseOk(translations);
	}

	@Path("retrieveMyTranslationsOfAnEntityVideo")
	@POST
	@Produces("application/json")
	public Response retrieveMyTranslationsOfAnEntityVideo(@HeaderParam("token") String token,
			@HeaderParam("languageFrom") Language languageFrom, @HeaderParam("languageTo") Language languageTo, 
			@HeaderParam("entityVideo") EntityVideoDTO entityVideo) {

		User user = UsersCache.getInstance().getUser(token);

		List<UsersTranslations> usersTranslations = UsersTranslationsDAO.retrieveUsersTranslationsOfSpecificEntityVideo(user.getEmail(),
				languageFrom, languageTo, entityVideo.getName());
		List<Translation> translations = UserTranslationsMgr.convertUsersTranslationsToTranslation(usersTranslations);
		translations.addAll(TranslationDAO.getUsersPersonalTranslation(user, languageFrom.name(), languageTo.name()));
		return Responses.responseOk(translations);
	}

	@Path("retrieveMyEntityvideos")
	@POST
	@Produces("application/json")
	public Response retrieveMyEntityvideos(@HeaderParam("token") String token,
			@HeaderParam("languageFrom") Language languageFrom, @HeaderParam("languageTo") Language languageTo) {

		User user = UsersCache.getInstance().getUser(token);

		List<UsersTranslations> usersTranslations = UsersTranslationsDAO.retrieveUsersTranslations(user.getEmail(),
				languageFrom, languageTo);
		List<Translation> translations = UserTranslationsMgr.convertUsersTranslationsToTranslation(usersTranslations);
		translations.addAll(TranslationDAO.getUsersPersonalTranslation(user, languageFrom.name(), languageTo.name()));

		Set<EntityVideoDTO> entityVideosDTO = translations.stream().map(translation -> translation.getSubtitleDTOToTranslate().getEntityvideoDTO()).collect(Collectors.toSet());

		return Responses.responseOk(entityVideosDTO);
	}

	@Path("removeMyTranslation")
	@POST
	@Produces("application/json")
	public Response removeMyTranslation(@HeaderParam("token") String token,
			@HeaderParam("translation") Translation translation) {

		User user = UsersCache.getInstance().getUser(token);

		if (translation.isPersonalTranslation()) {
			UsersPersonalTranslationsDao.removeUsersPersonalTranslations(user.getEmail(), translation.getId());
		} else {
			UsersTranslationsDAO.removeUsersTranslations(user.getEmail(), translation.getId());
		}

		return Response.ok().header(ACCESS_CONTROL_ALLOW_ORIGIN, "*").build();
	}

	@Path("translationInfos")
	@POST
	@Produces("application/json")
	public Response translationInfo(@HeaderParam("translation") Translation translation) {

		Subtitle subtitle = SubtitleDAO.getSubtitleById(translation.getSubtitleDTOToTranslate().getId());
		Entityvideo entityVideo = subtitle.getEntityvideo();
		EntityVideoDTO entityVideoDTO = new EntityVideoDTO(entityVideo.getId(), entityVideo.getName(), 
								entityVideo.getNumepisode(), entityVideo.getNumseason(), entityVideo.getPoster());
		return Responses.responseOk(entityVideoDTO);
	}

	@Path("addTranslation")
	@POST
	@Produces("application/json")
	@Consumes("application/json")
	public Response addTranslation(@HeaderParam("token") String token,
			@HeaderParam("translation") Translation translation) {
		User user = UsersCache.getInstance().getUser(token);
		UsersPersonalTranslations usersPersonalTranslations = new UsersPersonalTranslations();
		usersPersonalTranslations.setEmail(user.getEmail());
		usersPersonalTranslations.setExpr1(translation.getSubtitleDTOToTranslate().getExpression());
		usersPersonalTranslations.setExpr2(translation.getSubtitleDTOTranslated().getExpression());
		usersPersonalTranslations.setLanguageFrom(translation.getSubtitleDTOToTranslate().getLanguage());
		usersPersonalTranslations.setLanguageTo(translation.getSubtitleDTOTranslated().getLanguage());

		PersistenceUtils.persistObject(usersPersonalTranslations);
		return Response.ok().header(ACCESS_CONTROL_ALLOW_ORIGIN, "*").build();
	}

	@Path("translationsAround")
	@GET
	@Produces("application/json")
	public Response getTranslationsAround(@HeaderParam("token") String token,
			@HeaderParam("translation") Translation translation) {
		User user = UsersCache.getInstance().getUser(token);
		Set<Translation> translationsAround = TranslationDAO.getSubtitlesBefore(user, translation, 3);
		translationsAround.addAll(TranslationDAO.getSubtitlesAfter(user, translation, 2));

		List<Translation> translationsAroundOrdered = new ArrayList<>(translationsAround);
		Collections.sort(translationsAroundOrdered, new Comparator<Translation>() {

			@Override
			public int compare(Translation t1, Translation t2) {
				return t1.getSubtitleDTOToTranslate().getTimebegin().compareTo(t2.getSubtitleDTOToTranslate().getTimebegin());
			}

			
		});
		return Responses.responseOk(translationsAroundOrdered);
	}

	@Path("allTranslations")
	@GET
	@Produces("application/json")
	public Response getAllTranslations(@HeaderParam("token") String token,
			@HeaderParam("translation") Translation translation) {
		User user = UsersCache.getInstance().getUser(token);
		Set<Translation> allTranslations = TranslationDAO.translateAllEntityVideo(translation, user);

		List<Translation> translationsOrdered = new ArrayList<>(allTranslations);
		Collections.sort(translationsOrdered, new Comparator<Translation>() {

			@Override
			public int compare(Translation t1, Translation t2) {
				return t1.getSubtitleDTOToTranslate().getTimebegin().compareTo(t2.getSubtitleDTOToTranslate().getTimebegin());
			}

			
		});
		return Responses.responseOk(translationsOrdered);
	}

	@Path("/{videoName}/{episode}/{season}/{languageFrom}/{languageTo}")
	@GET
	@Produces("application/json")
	public Response getSubtitles(@PathParam("videoName") String videoName, @PathParam("episode") Integer numEpisode,
			@PathParam("season") Integer numSeason, @PathParam("languageFrom") Language languageFrom, 
			@PathParam("languageTo") Language languageTo, @HeaderParam("token") String token) {
		User user = UsersCache.getInstance().getUser(token);
		Set<Translation> entityVideoTranslations = TranslationDAO.translateAllEntityVideo(videoName, numEpisode, numSeason, languageFrom, languageTo, user);
		return Responses.responseOk(entityVideoTranslations);
	}

	@Path("integrateFile")
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response integrateFile(@FormDataParam("file") InputStream fileStream) {

		writeToFile(fileStream, "C:\\Users\\TOSHIBA\\Desktop\\hello2.srt");
		return Response.ok().header(ACCESS_CONTROL_ALLOW_ORIGIN, "*").build();
	}

	private static void writeToFile(InputStream uploadedInputStream, String uploadedFileLocation) {

		try(OutputStream out = new FileOutputStream(new File(uploadedFileLocation))) {
			int read = 0;
			byte[] bytes = new byte[1024];

			while ((read = uploadedInputStream.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
		} catch (IOException e) {
			LOGGER.error("Problem in uploading writing stream in file");
		}

	}
}
