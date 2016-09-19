package fr.gemeroi.services;

import java.util.List;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.json.JSONException;

import fr.gemeroi.common.utils.Language;
import fr.gemeroi.persistence.bean.User;
import fr.gemeroi.persistence.bean.UsersTranslations;
import fr.gemeroi.persistence.dao.SubtitleDAO;
import fr.gemeroi.persistence.dao.UsersTranslationsDAO;
import fr.gemeroi.persistence.utils.PersistenceUtils;
import fr.gemeroi.services.reponses.Responses;
import fr.gemeroi.translation.Translate;
import fr.gemeroi.translation.Translation;
import fr.gemeroi.user.creation.UsersCache;
import fr.gemeroi.user.translation.UserTranslationsMgr;

@Path("/translation")
public class TranslationServices {

	  @Path("/{word}/{language1}/{language2}")
	  @GET
	  @Produces("application/json")
	  public Response translate(@PathParam("word") String wordToTranslate, @PathParam("language1") Language language1, 
			  @PathParam("language2") Language language2, @HeaderParam("token") String token) throws JSONException {
		User user = UsersCache.getUser(token);
		Set<Translation> wordTranslatedList = Translate.translate(wordToTranslate, language1, language2, user);

		return Responses.responseOk(wordTranslatedList);
	  }

	  @Path("saveTranslation")
	  @POST
	  @Produces("application/json")
	  @Consumes("application/json")
	  public Response saveTranslation(@HeaderParam("token") String token, @HeaderParam("translation") Translation trans) throws JSONException {

		User user = UsersCache.getUser(token);
		UsersTranslations usersTranslations = new UsersTranslations(user.getEmail(), SubtitleDAO.getSubtitleById(trans.getSubtitleDTOToTranslate().getId()), SubtitleDAO.getSubtitleById(trans.getSubtitleDTOTranslated().getId()));
		PersistenceUtils.persistObject(usersTranslations);

		return Response.ok()
				.header("Access-Control-Allow-Origin", "*")
				.build();
	  }

	  @Path("retrieveMyTranslations")
	  @POST
	  @Produces("application/json")
	  public Response retrieveMyTranslations(@HeaderParam("token") String token, @HeaderParam("languageFrom") Language languageFrom, @HeaderParam("languageTo") Language languageTo) throws JSONException {

		User user = UsersCache.getUser(token);

		List<UsersTranslations> usersTranslations = UsersTranslationsDAO.retrieveUsersTranslations(user.getEmail(), languageFrom, languageTo);
		List<Translation> translations = UserTranslationsMgr.convertUsersTranslationsToTranslation(usersTranslations);

		return Responses.responseOk(translations);
	  }

	  @Path("removeMyTranslation")
	  @POST
	  @Produces("application/json")
	  public Response removeMyTranslation(@HeaderParam("token") String token, @HeaderParam("translation") Translation translation) throws JSONException {

		User user = UsersCache.getUser(token);

		UsersTranslationsDAO.removeUsersTranslations(user.getEmail(), translation.getId());
		return Response.ok()
					   .header("Access-Control-Allow-Origin", "*")
					   .build();
	  }

	  @Path("addTranslation")
	  @POST
	  @Produces("application/json")
	  @Consumes("application/json")
	  public Response addTranslation(@HeaderParam("token") String token, @HeaderParam("translation") Translation translation) throws JSONException {
		  System.out.println(translation);
		  return Response.ok()
				   .header("Access-Control-Allow-Origin", "*")
				   .build();
	  }
}
