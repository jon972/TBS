package fr.gemeroi.servlets;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataBodyPart;
import com.sun.jersey.multipart.FormDataParam;

import fr.gemeroi.common.utils.LanguageEnum;
import fr.gemeroi.population.DataFeeder;

@Path("/file")
public class UploadSubtitleFile {
	private static final String SERVER_UPLOAD_LOCATION_FOLDER = "C:\\Users\\TOSHIBA\\Desktop\\Upload_Files\\";

	/**
	 * Upload a File
	 */

	@POST
	@Path("/upload")
	@Consumes(MediaType.MULTIPART_FORM_DATA) 
	public Response uploadFile(
			@FormDataParam("file") List<FormDataBodyPart> contentDispositionHeader,
			@FormDataParam("videoName") String videoName, @FormDataParam("videoType") String videoType, 
			@FormDataParam("season") String numSeason, @FormDataParam("episode") String numEpisode,
			@FormDataParam("language") String language) {

		for(FormDataBodyPart formDataBodyPart : contentDispositionHeader) {
			InputStream is = contentDispositionHeader.get(0).getEntityAs(InputStream.class);
			String filePath = formDataBodyPart.getContentDisposition().getFileName();
			saveFile(is, filePath);
			DataFeeder.persistSubtitles(new File(filePath), formDataBodyPart.getName(), videoType, LanguageEnum.getInstance(language));
		}

		return Response.status(200).build();

	}

	// save uploaded file to a defined location on the server
	private void saveFile(InputStream uploadedInputStream,
			String serverLocation) {

		try {
			OutputStream outpuStream = new FileOutputStream(new File(serverLocation));
			int read = 0;
			byte[] bytes = new byte[1024];

			outpuStream = new FileOutputStream(new File(serverLocation));
			while ((read = uploadedInputStream.read(bytes)) != -1) {
				outpuStream.write(bytes, 0, read);
			}
			outpuStream.flush();
			outpuStream.close();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

}
