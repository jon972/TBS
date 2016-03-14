package fr.gemeroi.services;

import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import fr.gemeroi.persistence.bean.UsersTranslations;
import fr.gemeroi.persistence.utils.PersistenceUtils;

@Path("/save")
public class SaveTranslationService {
	@Path("/saveTranslation")
	@POST
	@Produces("application/json")
	public Response saveTranslation(
			@HeaderParam("token") String token, @HeaderParam("language1") String language1, 
			@HeaderParam("language2") String language2, @HeaderParam("entityVideoId") Integer entityVideoId, 
			@HeaderParam("timeBegin") Integer timeBegin) {
		UsersTranslations usersTranslationsEntry = 
				new UsersTranslations(token, entityVideoId, language1, language2, timeBegin);
		PersistenceUtils.persistObject(usersTranslationsEntry);
		return null;
	}
}
