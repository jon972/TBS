package fr.gemeroi.services.reponses;

import static fr.gemeroi.services.reponses.HeadersResponses.ACCESS_CONTROL_ALLOW_ORIGIN;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Responses {

	private Responses() {}

	public static Response responseOk(Object entity) {
		final GsonBuilder builder = new GsonBuilder();
		final Gson gson = builder.create();

		return Response.ok(gson.toJson(entity) ,MediaType.APPLICATION_JSON)
				.header(ACCESS_CONTROL_ALLOW_ORIGIN, "*")
				.build();
	}

	public static Response responseError() {
		return Response.serverError()
				.header(ACCESS_CONTROL_ALLOW_ORIGIN, "*")
				.build();
	}
}
