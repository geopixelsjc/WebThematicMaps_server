package geopixel.rest;
 
import geopixel.model.geolocation.Endereco;
import geopixel.model.geolocation.Geometry;
import geopixel.model.legacy.dto.Acesso;
import geopixel.service.TerracoreService;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.crypto.NoSuchPaddingException;
import javax.ws.rs.GET;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/json")
public class JSONService {
	
	@GET
	@Path("{checkKeyinSession}")
	public Response checkKey(@MatrixParam("key") String key) {
		boolean valid = false;
		try {
			valid = TerracoreService.checkKey(key);
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}
		
		return Response.status(200).entity("check: "+valid).build();
	}
	
	@GET
	@Path("{access}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Acesso getAccsess(@MatrixParam("login") String login, @MatrixParam("pass") String password) {
		Acesso acesso = new Acesso();
		
		try {
			acesso = TerracoreService.login(login, password);
			//limpa login e senha
			acesso.setLogin("invisivel");
			acesso.setSenha("invisivel");
		} catch (InvalidKeyException | NoSuchAlgorithmException
				| NoSuchPaddingException | IOException | SQLException e) {
			e.printStackTrace();
		}
		return acesso;
	}
	
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
			e.printStackTrace();
		}

		return geom;
	}
}