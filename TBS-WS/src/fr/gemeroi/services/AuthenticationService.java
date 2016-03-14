package fr.gemeroi.services;


import java.util.UUID;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import org.apache.commons.collections.map.HashedMap;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import fr.gemeroi.persistence.bean.User;
import fr.gemeroi.persistence.session.SessionMgr;
import fr.gemeroi.session.TokenMgr;

@Path("/authentication")
public class AuthenticationService {
//
//	@Path("/token")
//	@GET 
//	@Produces("text/plain")
//	@Consumes(MediaType.MULTIPART_FORM_DATA) 
//	public String getToken(
//	        @FormParam( "username" ) String username,
//	        @FormParam( "password" ) String password) {
//		//Récupère l'utilisateur en base
//		//Renvoie le token
//		
//		return null;
//	}
}
