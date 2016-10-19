package org.talem.messenger.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import javax.xml.ws.spi.http.HttpHandler;

@Path("annotation")
@Consumes(MediaType.TEXT_PLAIN)
@Produces(MediaType.TEXT_PLAIN)
public class AnnotationParams {
	@GET
	@Path("general")
	public String getAnnotationParam(@MatrixParam("param")String matrixParam,
									@HeaderParam("headerName")String headerParam,
									@CookieParam("coockiName") String cookieParam){
		return "Matrix: " + matrixParam + "Header: " + headerParam + "Cookie: " + cookieParam ;
	}
	
	@GET
	@Path("context")
	public String getParamsByContext(@Context UriInfo uriInfo,
									@Context HttpHeaders header){
		//header.getLotsOfStuff
		//uriInfo.getLotsofStuff;
		return "Test";
	}
	
}
