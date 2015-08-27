package geopixel.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

// http://localhost:8080/ThematicMap/rest/thematic/properties

@Path("/thematic")
public class ThematicEndpoint {

        @GET
        @Path("/")
        @Produces(MediaType.APPLICATION_JSON)
        public Response thematicEndpoint() {
                String result = "/thematic";
                return Response.status(200).entity(result).build();
        }

        @GET
        @Path("/properties")
        @Produces(MediaType.APPLICATION_JSON)
        public Response propertiesEndpoint() {
                String result = "/properties";
                return Response.status(200).entity(result).build();
        }
        
        @GET
        @Path("/generate")
        @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
        public Response generateEndopoint(
                                          @QueryParam("name") String name,
                                          @QueryParam("table") String table,
                                          @QueryParam("column") String column) {
                String result = "/generate";
                return Response.status(200).entity(result).build();
        }
        
}
