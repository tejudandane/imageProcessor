package rest.controller;

import java.io.InputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
//import org.glassfish.jersey.media.multipart.FormDataParam;
//import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import processor.*;

@Path("/")
public class ImageController {

	@POST
	@Path("/RotateDegree")
	@Produces("image/png")
	public Response RotateDegree(
			@QueryParam("Path") String path,
			@QueryParam("Degree") int degree
			)
	{
		ImageProcessor ip = new ImageProcessor();
		byte[] image = ip.rotateDegreeImage(path, degree);
		return Response.ok(image).build();
	}
	
	@POST
	@Path("/Flip")
	@Produces("image/png")
	public Response FlipImage(
			@QueryParam("Path") String path,
			@QueryParam("Type") int type)
			//) {
	{
		System.out.println("Rotate image Path " + path);
		ImageProcessor ip = new ImageProcessor();
		//ImageProcessor ip = new ImageProcessor();
		return Response.ok(ip.flipDirectionImage(path, type)).build();
		
		//ip.flipImage(imagePath, type);
		//return "Image Flipped";
	}
	
	@POST
	@Path("/GrayScale")
	@Produces("image/png")
	public Response GrayScale(
			@QueryParam("Path") String path)
	{
		//System.out.println("Rotate image Path " + imagePath);
		ImageProcessor ip = new ImageProcessor();
		return Response.ok(ip.grayScaleImage(path)).build();
		//return "Image Grayscaled";
	}
	
	@POST
	@Path("/Resize")
	@Produces("image/png")
	public Response Resize(
			@QueryParam("Path") String path,
			@QueryParam("Height") int height,
			@QueryParam("Width") int width
			)
	{
		ImageProcessor ip = new ImageProcessor();
		byte[] image = ip.resizeImage(path, height, width);
		return Response.ok(image).build();
	}
	
	@POST
	@Path("/Thumbnail")
	@Produces("image/png")
	public Response Thumbnail(
			@QueryParam("Path") String path)
	{
		//System.out.println("Rotate image Path " + imagePath);
		ImageProcessor ip = new ImageProcessor();
		byte[] image = ip.resizeImage(path, 120, 120);
		return Response.ok(image).build();
	}
	
	@POST
	@Path("/Rotate")
	@Produces("image/png")
	public Response Rotate(
			@QueryParam("Path") String path,
			@QueryParam("Direction") String direction)
	{
		//System.out.println("Rotate image Path " + imagePath);
		ImageProcessor ip = new ImageProcessor();
		byte[] image = ip.rotateImage(path, direction);
		return Response.ok(image).build();
		
		//ip.rotate(direction);
		//return "Image Rotated";
	}
	
	/*
	@POST
	@Path("/GScale")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadFile(
			@FormDataParam("file") InputStream uploadedInputStream,
			@FormDataParam("file") FormDataContentDisposition fileDetail) {
		// check if all form parameters are provided
		if (uploadedInputStream == null || fileDetail == null)
			return Response.status(400).entity("Invalid form data").build();
		// create our destination folder, if it not exists
		
		return Response.status(200)
				.entity("File saved to ").build();
	}
	*/
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
