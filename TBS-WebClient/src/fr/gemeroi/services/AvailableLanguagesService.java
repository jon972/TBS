package fr.gemeroi.services;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.json.JSONException;

import fr.gemeroi.common.utils.Language;
import fr.gemeroi.services.reponses.Responses;

@ApplicationScoped
@Path("/availableLanguages")
public class AvailableLanguagesService {
	
	@Path("/allLanguages")
	@GET
	@Produces("application/json")
	public Response getAllLanguages() throws JSONException {
		return Responses.responseOk(Language.values());
	}

}
