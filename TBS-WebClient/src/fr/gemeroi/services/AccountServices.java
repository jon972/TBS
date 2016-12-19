package fr.gemeroi.services;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Any;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import fr.gemeroi.persistence.bean.User;
import fr.gemeroi.persistence.dao.model.UsersDAO;
import fr.gemeroi.services.reponses.Responses;
import fr.gemeroi.user.creation.UserFactory;
import fr.gemeroi.user.creation.UsersCache;

@ApplicationScoped
@Path("/account")
public class AccountServices {

	@Inject
	UsersDAO usersDAO;

	@Inject
	UserFactory userFactory;

	@Path("/createAccount")
	@POST
	@Produces("application/json")
	public Response createAccount(@HeaderParam("login") String login,
								  @HeaderParam("email") String email,
								  @HeaderParam("password") String password) {
		User user = userFactory.createUser(login, email, password);
		
		return Responses.responseOk(user);
	}

	@Path("/login")
	@GET
	@Produces("application/json")
	public Response login(@HeaderParam("email") String email,
						  @HeaderParam("password") String password) {
		User user = usersDAO.retrieveUser(email, password);

		if (user != null) {
			String token = user.getToken();
			UsersCache.addUser(token, user);

			return Responses.responseOk(user);
		}

		return Responses.responseError();
	}
}
