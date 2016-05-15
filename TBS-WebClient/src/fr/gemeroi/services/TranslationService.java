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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import fr.gemeroi.common.utils.LanguageEnum;
import fr.gemeroi.persistence.bean.Subtitle;
import fr.gemeroi.persistence.bean.User;
import fr.gemeroi.persistence.bean.UsersTranslations;
import fr.gemeroi.persistence.utils.PersistenceUtils;
import fr.gemeroi.persistence.utils.query.QuerySubtitleUtils;
import fr.gemeroi.persistence.utils.query.QueryUsersTranslationsUtils;
import fr.gemeroi.session.TokenMgr;
import fr.gemeroi.session.UserTranslationsMgr;
import fr.gemeroi.translation.Translate;
import fr.gemeroi.translation.Translation;
import fr.gemeroi.translation.dto.SubtitleDTO;

@Path("/translation")
public class TranslationService {

	  // http://localhost:portNum/TBS-WS/rest/translation/Hi/English/French
	  @Path("/{word}/{language1}/{language2}")
	  @GET
	  @Produces("application/json")
	  public Response translate(@PathParam("word") String wordToTranslate, @PathParam("language1") String language1, 
			  @PathParam("language2") String language2, @HeaderParam("token") String token) throws JSONException {
		User user = TokenMgr.tokensMap.get(token);
		Set<Translation> translations = UserTranslationsMgr.userTranslations.get(user);
		List<Translation> wordTranslatedList = Translate.translate(wordToTranslate, LanguageEnum.getInstance(language1), LanguageEnum.getInstance(language2), user);

		if(translations != null) {
			for(Translation tr : wordTranslatedList) {
				if (translations.contains(tr)) {
					tr.setSaved(true);
				}
			}
		}

		final GsonBuilder builder = new GsonBuilder();
		final Gson gson = builder.create();
		return Response.ok(gson.toJson(wordTranslatedList) ,MediaType.APPLICATION_JSON)
				.header("Access-Control-Allow-Origin", "*")
				.build();
	  }

	  @Path("saveTranslation")
	  @POST
	  @Produces("application/json")
	  @Consumes("application/json")
	  public Response saveTranslation(@HeaderParam("token") String token, @HeaderParam("translation") Translation trans) throws JSONException {

		User user = TokenMgr.tokensMap.get(token);
		UsersTranslations usersTranslations = new UsersTranslations(user.getEmail(), QuerySubtitleUtils.getSubtitleById(trans.getSubtitleDTOToTranslate().getId()), QuerySubtitleUtils.getSubtitleById(trans.getSubtitleDTOTranslated().getId()));
		PersistenceUtils.persistObject(usersTranslations);
		UserTranslationsMgr.updateUserTranslations(user, usersTranslations);

		return Response.ok()
				.header("Access-Control-Allow-Origin", "*")
				.build();
	  }

	  @Path("retrieveMyTranslations")
	  @POST
	  @Produces("application/json")
	  public Response retrieveMyTranslations(@HeaderParam("token") String token) throws JSONException {

		User user = TokenMgr.tokensMap.get(token);
		Set<Translation> translations = UserTranslationsMgr.userTranslations.get(user);

		final GsonBuilder builder = new GsonBuilder();
		final Gson gson = builder.create();

		return Response.ok(gson.toJson(translations) ,MediaType.APPLICATION_JSON)
				.header("Access-Control-Allow-Origin", "*")
				.build();
	  }

	  @Path("removeMyTranslation")
	  @POST
	  @Produces("application/json")
	  public Response removeMyTranslation(@HeaderParam("token") String token, @HeaderParam("translation") Translation translation) throws JSONException {

		User user = TokenMgr.tokensMap.get(token);
		Set<Translation> translations = UserTranslationsMgr.userTranslations.get(user);
		translations.remove(translation);

		QueryUsersTranslationsUtils.removeUsersTranslations(user.getEmail(), translation.getId());
		return Response.ok()
					   .header("Access-Control-Allow-Origin", "*")
					   .build();
	  }
}
