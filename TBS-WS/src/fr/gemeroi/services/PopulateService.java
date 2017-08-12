package fr.gemeroi.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Random;

import javax.ws.rs.Consumes;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.sun.jersey.multipart.FormDataParam;

import fr.gemeroi.common.utils.Language;
import fr.gemeroi.population.SubtitlePersistor;
import static fr.gemeroi.services.reponses.HeadersResponses.ACCESS_CONTROL_ALLOW_ORIGIN;;

@Path("/populate")
public class PopulateService {

	private static final Logger LOGGER = Logger.getLogger(PopulateService.class);

	@Path("/integrateFile")
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response integrateFile(@FormDataParam("file") InputStream fileStream, @HeaderParam("language") Language language, @HeaderParam("serieName") String serieName) {
		String subFileName = "C:\\Users\\TOSHIBA\\AppData\\Roaming\\TBS\\" + randomFileName();
		File subFile = new File(subFileName);

		writeToFile(fileStream, subFileName);
		SubtitlePersistor subtitlePersistor = new SubtitlePersistor("(.*)(\\d+)x(\\d+)(.*)");
		subtitlePersistor.persistSubtitles(subFile, serieName, language);
		return Response.ok().header(ACCESS_CONTROL_ALLOW_ORIGIN, "*").build();
	}

	private static void writeToFile(InputStream uploadedInputStream, String uploadedFileLocation) {

		try(OutputStream out = new FileOutputStream(new File(uploadedFileLocation))) {
			int read = 0;
			byte[] bytes = new byte[1024];

			while ((read = uploadedInputStream.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
		} catch (IOException e) {
			LOGGER.error("Problem in uploading writing stream in file");
		}

	}

	private static String randomFileName() {
		Random rand = new Random();
		
		int i = 0;
		StringBuilder fileNameBuilder = new StringBuilder();
		while(i < 20) {
			char c = (char)(rand.nextInt(26) + 'a');
			fileNameBuilder.append(c);
		}
		
		return new String(fileNameBuilder);
	}

}
