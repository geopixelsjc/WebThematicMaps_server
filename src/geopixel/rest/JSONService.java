package geopixel.rest;
 
import geopixel.model.external.GenericTable;
import geopixel.model.geolocation.Endereco;
import geopixel.model.geolocation.Geometry;
import geopixel.model.hb.dto.AppDicionarioDado;
import geopixel.model.hb.dto.AppTabela;
import geopixel.model.hb.dto.AppTema;
import geopixel.model.legacy.dto.Acesso;
import geopixel.service.TerracoreService;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.crypto.NoSuchPaddingException;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/json")
public class JSONService {
	
	@GET
	@Path("/userTableData")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public GenericTable getPhysicalTable(
			@QueryParam("tabela_id") int tabela_id,
			@QueryParam("nomeTabela") String nomeTabela,
			@QueryParam("conexao") String conn,
			@QueryParam("limit") int limit,
			@QueryParam("offset") int offset) {

		GenericTable table = new GenericTable();
		AppTabela tabela = new AppTabela();
		tabela.setTblId(tabela_id);
		tabela.setNome(nomeTabela);
		tabela.setUrlConexaoBanco(conn);
			
		try {
			table = TerracoreService.getPhysicalTable(tabela, limit, offset);
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}
			
		return table;
	}
	
	@GET
	@Path("/userTables")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public ArrayList<AppTabela> getUserTable(
			@QueryParam("theme_id") int theme_id ) {

		ArrayList<AppTabela> tabelas = new ArrayList<AppTabela>();

			try {
				tabelas = TerracoreService.getUserTableByThemeId(theme_id);
			} catch (IOException | SQLException e) {
				e.printStackTrace();
			}
	
		return tabelas;
	}
	
	@GET
	@Path("/tableDataDictionaries")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public ArrayList<AppDicionarioDado> UserTableDataDictionaries(
		@QueryParam("table_id") int table_id) {

			ArrayList<AppDicionarioDado> dicionarioDados = new ArrayList<AppDicionarioDado>();
			AppTabela tabela = new AppTabela();
			tabela.setTblId(table_id);

		try {
			dicionarioDados = TerracoreService.getTableAttributesDD(tabela);
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}
	
		return dicionarioDados;
	}
	
	
	@GET
	@Path("/themesByKey/{param}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public ArrayList<AppTema> getThemeByKey(@PathParam("param") String key) {

		ArrayList<AppTema> temas = new ArrayList<AppTema>();

			try {
				temas = TerracoreService.getThemesByKey(key);
			} catch (IOException | SQLException e) {
				e.printStackTrace();
			}
	
		return temas;
	}
	
	
	@GET
	@Path("/checkKeyinSession/{param}")
	public Response checkKey(@PathParam("param") String key) {
		boolean valid = false;
		try {
			valid = TerracoreService.checkKey(key);
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}
		
		return Response.status(200).entity("check: "+valid).build();
	}
	
	@POST
	@Path("/checkAccess")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Acesso getAccsess(@FormParam("login") String login, @FormParam("pass") String password) {
		Acesso acesso = new Acesso();
		
		try {
			acesso = TerracoreService.login(login, password);
			//limpa login e senha
			acesso.setLogin("oculto");
			acesso.setSenha("oculto");
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