package fr.gemeroi.services;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import fr.gemeroi.common.utils.LanguageEnum;
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
}
