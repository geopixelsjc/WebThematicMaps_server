package geopixel.rest;
 
import geopixel.model.geolocation.Endereco;
import geopixel.model.geolocation.Geometry;
import geopixel.service.TerracoreService;

import java.io.IOException;
import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/json")
public class JSONService {

	@GET
	@Path("/address/{param}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public ArrayList<Endereco> getAddress(@PathParam("param") String address) {

		ArrayList<Endereco> enderecos = new ArrayList<Endereco>();

		try {
			enderecos = TerracoreService.execAlphaNumericLocation(address);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return enderecos;
	}

	@GET
	@Path("/geom/{param}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public ArrayList<Geometry> getGeom(@PathParam("param") String address) {

		ArrayList<Geometry> geom = new ArrayList<Geometry>();

		try {
			geom = TerracoreService.execGeoLocation(address);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return geom;
	}
}