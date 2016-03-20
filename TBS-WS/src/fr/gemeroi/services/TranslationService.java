package fr.gemeroi.services;

import java.util.ArrayList;
import java.util.List;

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
import fr.gemeroi.persistence.bean.User;
import fr.gemeroi.persistence.bean.UsersTranslations;
import fr.gemeroi.persistence.utils.PersistenceUtils;
import fr.gemeroi.persistence.utils.query.QueryUsersTranslationsUtils;
import fr.gemeroi.session.TokenMgr;
import fr.gemeroi.translation.Translate;
import fr.gemeroi.translation.Translation;

@Path("/translationService")
public class TranslationService {
	  // http://localhost:8081/TBS-WS/rest/translationService/Hi/English/French
	  @Path("/{word}/{language1}/{language2}")
	  @GET
	  @Produces("application/json")
	  public Response translate(@PathParam("word") String wordToTranslate, @PathParam("language1") String language1, @PathParam("language2") String language2) throws JSONException {
		List<Translation> wordTranslatedList = Translate.translate(wordToTranslate, LanguageEnum.getInstance(language1), LanguageEnum.getInstance(language2));
		
		final GsonBuilder builder = new GsonBuilder();
		final Gson gson = builder.create();
		return Response.ok(gson.toJson(wordTranslatedList) ,MediaType.APPLICATION_JSON)
				.header("Access-Control-Allow-Origin", "*")
				.build();
	  }

	  @Path("saveTranslation")
	  @POST
	  @Produces("application/json")
	  public Response saveTranslation(@HeaderParam("token") String token, @HeaderParam("expr1") String expr1, @HeaderParam("expr2") String expr2) throws JSONException {

		User user = TokenMgr.tokensMap.get(token);
		UsersTranslations usersTranslations = new UsersTranslations(user.getEmail(), expr1, expr2);
		PersistenceUtils.persistObject(usersTranslations);
		return Response.ok()
				.header("Access-Control-Allow-Origin", "*")
				.build();
	  }

	  @Path("retrieveMyTranslations")
	  @POST
	  @Produces("application/json")
	  public Response retrieveMyTranslations(@HeaderParam("token") String token) throws JSONException {

		User user = TokenMgr.tokensMap.get(token);
		List<UsersTranslations> usersTranslations = QueryUsersTranslationsUtils.retrieveUsersTranslations(user.getEmail());
		List<Translation> translations = convertUsersTranslationsToTranslation(usersTranslations);

		final GsonBuilder builder = new GsonBuilder();
		final Gson gson = builder.create();

		return Response.ok(gson.toJson(translations) ,MediaType.APPLICATION_JSON)
				.header("Access-Control-Allow-Origin", "*")
				.build();
	  }

	  private List<Translation> convertUsersTranslationsToTranslation(List<UsersTranslations> usersTranslations) {
		  List<Translation> translationsList = new ArrayList<>();
		  for(UsersTranslations ut : usersTranslations) {
			  translationsList.add(new Translation(ut.getExpr1(), ut.getExpr2()));
		  }
		  return translationsList;
	  }
}
