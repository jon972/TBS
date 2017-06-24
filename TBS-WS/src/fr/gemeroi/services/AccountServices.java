package fr.gemeroi.services;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import fr.gemeroi.persistence.bean.User;
import fr.gemeroi.persistence.dao.UsersDAO;
import fr.gemeroi.services.reponses.Responses;
import fr.gemeroi.user.creation.UserFactory;
import fr.gemeroi.user.creation.UsersCache;

@Path("/account")
public class AccountServices {
	@Path("/createAccount")
	@POST
	@Produces("application/json")
	public Response createAccount(@HeaderParam("login") String login,
								  @HeaderParam("email") String email,
								  @HeaderParam("password") String password) {
		User user = UserFactory.createUser(login, email, password);

		if (user != null) {
			String token = user.getToken();
			UsersCache.addUser(token, user);

			return Responses.responseOk(user);
		}

		return Responses.responseError();
	}

	@Path("/login")
	@GET
	@Produces("application/json")
	public Response login(@HeaderParam("email") String email,
						  @HeaderParam("password") String password) {
		User user = UsersDAO.retrieveUser(email, password);

		if (user != null) {
			String token = user.getToken();
			UsersCache.addUser(token, user);

			return Responses.responseOk(user);
		}

		return Responses.responseError();
	}
}
