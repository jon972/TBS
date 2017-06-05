package fr.gemeroi.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.json.JSONException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import fr.gemeroi.common.utils.Language;
import fr.gemeroi.persistence.bean.Entityvideo;
import fr.gemeroi.persistence.bean.User;
import fr.gemeroi.persistence.session.SessionMgr;
import fr.gemeroi.services.reponses.Responses;

@Path("/availableLanguages")
public class AvailableLanguagesService {

	@Path("/allLanguages")
	@GET
	@Produces("application/json")
	public Response getAllLanguages() throws JSONException {
		return Responses.responseOk(Language.values());
	}

}
