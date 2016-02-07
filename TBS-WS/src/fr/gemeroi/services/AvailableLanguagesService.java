package fr.gemeroi.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import fr.gemeroi.common.utils.LanguageEnum;

@Path("/availableLanguages")
public class AvailableLanguagesService {
	// http://localhost:8081/TBS-WS/rest/availableLanguages/allLanguages
	@Path("/allLanguages")
	@GET
	@Produces("application/json")
	public Response getAllLanguages() throws JSONException {		
		final GsonBuilder builder = new GsonBuilder();
		final Gson gson = builder.create();
		return Response.ok(gson.toJson(LanguageEnum.values()) ,MediaType.APPLICATION_JSON)
				.header("Access-Control-Allow-Origin", "*")
				.build();
	}

}
