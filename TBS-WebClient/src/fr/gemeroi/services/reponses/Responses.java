package fr.gemeroi.services.reponses;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Responses {

	public static Response responseOk(Object entity) {
		final GsonBuilder builder = new GsonBuilder();
		final Gson gson = builder.create();

		return Response.ok(gson.toJson(entity) ,MediaType.APPLICATION_JSON)
				.header("Access-Control-Allow-Origin", "*")
				.build();
	}

	public static Response responseError() {
		return Response.serverError()
				.header("Access-Control-Allow-Origin", "*")
				.build();
	}
}
