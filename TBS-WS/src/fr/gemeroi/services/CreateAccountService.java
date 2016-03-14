package fr.gemeroi.services;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import fr.gemeroi.persistence.bean.User;
import fr.gemeroi.persistence.utils.PersistenceUtils;
import fr.gemeroi.persistence.utils.query.QueryUsersUtils;
import fr.gemeroi.session.TokenMgr;

@Path("/signup")
public class CreateAccountService {
	@Path("/createAccount")
	@POST
	@Produces("application/json")
	public Response createAccount(@HeaderParam("login") String login,
								  @HeaderParam("email") String email,
								  @HeaderParam("password") String password) {
		User user = new User(login, email, password);
		PersistenceUtils.persistObject(user);
		String token = PersistenceUtils.getToken();
		TokenMgr.tokensMap.put(token, user);
		
		final GsonBuilder builder = new GsonBuilder();
		final Gson gson = builder.create();

		return Response.ok(gson.toJson(user) ,MediaType.APPLICATION_JSON)
				.header("Access-Control-Allow-Origin", "*")
				.build();
	}

	@Path("/login")
	@GET
	@Produces("application/json")
	public Response login(@HeaderParam("email") String email,
						  @HeaderParam("password") String password) {
		User user = new User(email, password);
		user = QueryUsersUtils.retrieveUser(user);
		String token = user.getToken();
		TokenMgr.tokensMap.put(token, user);
		
		final GsonBuilder builder = new GsonBuilder();
		final Gson gson = builder.create();

		return Response.ok(gson.toJson(user) ,MediaType.APPLICATION_JSON)
				.header("Access-Control-Allow-Origin", "*")
				.build();
	}
}
