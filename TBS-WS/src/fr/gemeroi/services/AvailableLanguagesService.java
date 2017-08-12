package fr.gemeroi.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import fr.gemeroi.common.utils.Language;
import fr.gemeroi.services.reponses.Responses;

@Path("/availableLanguages")
public class AvailableLanguagesService {

	@Path("/allLanguages")
	@GET
	@Produces("application/json")
	public Response getAllLanguages() {
		return Responses.responseOk(Language.values());
	}

}
