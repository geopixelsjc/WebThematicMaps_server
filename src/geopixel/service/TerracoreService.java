package geopixel.service;
 
import geopixel.model.geolocation.Endereco;
import geopixel.model.geolocation.Geometry;
import geopixel.model.legacy.bo.AcessoBo;
import geopixel.model.legacy.dto.Acesso;
import geopixel.utils.Cryptography;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.crypto.NoSuchPaddingException;

public class TerracoreService {

	private static String streetTable;
	private static String lotTable;
	private static String lotColumn1;
	private static String lotColumn2;
	private static String lotColumn3;
	
	private static String streetColumn;
	private static String tSpatialColumn;
	
	

	public TerracoreService() throws IOException {
		lotTable = "geo_lote";//PropertiesReader.getSearchProp().getProperty("prop.search.lotTable");
		lotColumn1 = "chave";//PropertiesReader.getSearchProp().getProperty("prop.search.lotColumn1");
		lotColumn2 = "chave";//PropertiesReader.getSearchProp().getProperty("prop.search.lotColumn2");
		lotColumn3 = "chave";//PropertiesReader.getSearchProp().getProperty("prop.search.lotColumn3");
		
		streetTable = "geo_eixo";//PropertiesReader.getSearchProp().getProperty("prop.search.streetTable");
		streetColumn = "nome";//PropertiesReader.getSearchProp().getProperty("prop.search.streetColumn");
	}
	
	public static boolean checkKey(String key) throws IOException, SQLException{
		boolean valid = false;
		String sql = "select * from app_acesso where chave = '"+key+"'";
		ResultSet rs = DataBaseService.buildSelect(sql);
		valid = rs.next();
		return valid;
	}
	
	public static Acesso login(String login, String password) throws IOException, SQLException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException{
		boolean valid = false;
		Acesso acesso = new Acesso();	
		
		String sql = "select * from app_acesso where login = '"+login+"' and senha = '"+password+"'";
		ResultSet rs = DataBaseService.buildSelect(sql);
		valid = rs.next();
		
			if(valid){
				acesso.setAscId(rs.getInt("asc_id"));
				acesso.setNome(rs.getString("nome"));
				acesso.setLogin(rs.getString("login"));
				acesso.setSenha(rs.getString("senha"));
				
				if(rs.getString("chave") == null){
					String key = generateAccessKey(acesso);
					acesso.setChave(key);
				}
				else{
					acesso.setChave(rs.getString("chave"));
				}
			}
			
		return acesso;
	}
	
	public static String generateAccessKey(Acesso entidade) throws NoSuchAlgorithmException{
		AcessoBo acessoBo =  new AcessoBo();
		Acesso acesso = new Acesso();
		Cryptography c = new Cryptography();
		String chave = c.generateHashMD5(entidade.getLogin()+entidade.getSenha());
		
		acesso.setAscId(entidade.getAscId());
		acesso.setLogin(entidade.getLogin());
		acesso.setNome(entidade.getNome());
		acesso.setSenha(entidade.getSenha());
		acesso.setChave(chave);
		
		acessoBo.saveOrUpdate(acesso);
		return chave;
	}


	/**
	 * Brief: executes search in the database of registration batches and
	 * streets by street names, and may return batches or streets depending on
	 * the type of input: numeric or textual or both when separated by commas.
	 * @throws IOException 
	 */
	public static ArrayList<Endereco> execAlphaNumericLocation(String search) throws IOException {
		new TerracoreService();
		String searchText = search.toUpperCase();

		ArrayList<Endereco> enderecos = new ArrayList<Endereco>();

		String streetName = "";
		String streetNumber = "";
		String street = "";
		String number = "";

		if (!searchText.matches("[0-9]+")) {
			String[] exp = searchText.split(",");
			street = exp[0];

			if (searchText != null && searchText != "" && !searchText.isEmpty()) {
				if (exp.length <= 1) {

					// ResultSet rs =
					// DataBaseService.buildSelect("select distinct "+streetColumn+","+streetColumn1+" from "+streetTable+" where "+streetColumn+" LIKE '%"
					// + street.trim() + "%' order by "+streetColumn);
					ResultSet rs = DataBaseService.buildSelect("select distinct " + streetColumn + " from "
							+ streetTable + " where " + "upper("+streetColumn +")"+" LIKE '%" + street.trim() + "%' order by "
							+ streetColumn);
					try {
						enderecos.clear();
						while (rs.next()) {
							Endereco endereco = new Endereco();
							endereco.setEndereco(rs.getString(1));
							// endereco.setId(rs.getInt(2));
							enderecos.add(endereco);
						}

					} catch (SQLException e) {
						e.printStackTrace();
					}

				} else {
					number = exp[1];
					ResultSet rs = DataBaseService.buildSelect("select distinct " + lotColumn1 + "," + lotColumn2 + ","
							+ lotColumn3 + " from " + lotTable + " where " + "cast(" +lotColumn1+" as text)" + " LIKE '%" + street.trim()
							+ "%'" + " and " +"cast(" + lotColumn3 + " as text)" + " LIKE '%" + "" + number.trim() + "%'" + " order by "
							+ lotColumn3);

					enderecos.clear();

					try {

						while (rs.next()) {
							Endereco endereco = new Endereco();
							streetName = rs.getString(1);
							streetNumber = rs.getString(3);
							endereco.setEndereco(streetName);
							endereco.setNumero(streetNumber);
							endereco.setLote(rs.getString(2));
							enderecos.add(endereco);

						}
					} catch (SQLException e) {
						e.printStackTrace();
					}

				}

			}
		} else {
			if (searchText != null && searchText != "" && !searchText.isEmpty()) {

				enderecos.clear();

				ResultSet rs = DataBaseService.buildSelect("select distinct " + lotColumn2 + "," + lotColumn1 + ","
						+ lotColumn3 + " from " + lotTable + " where " + lotColumn2 + " like '" + searchText.trim()
						+ "%'");
				try {

					while (rs.next()) {
						Endereco endereco = new Endereco();
						endereco.setLote(rs.getString(1));
						endereco.setEndereco(rs.getString(2));
						endereco.setNumero(rs.getString(3));
						enderecos.add(endereco);

					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return enderecos;
	}

	/**
	 * @param search
	 * @return
	 * @throws IOException 
	 */
	public static ArrayList<Geometry> execGeoLocation(String search) throws IOException {
		new TerracoreService();
		tSpatialColumn = "ST_AsText(geom)";
		
		String searchText = search.toUpperCase();
		
		ArrayList<Geometry> geom = new ArrayList<Geometry>();
		String street = "";
		String number = "";
		String lote = "";

		if (!searchText.matches("[0-9]+")) {
			String[] exp = searchText.split(",");
			street = exp[0];
			String[] aux = exp[1].split(" ");
			number = aux[0];
			lote = aux[1];

			if (searchText != null && searchText != "" && !searchText.isEmpty()) {
				if (number.equals("NULL") || number.equals("")) {
					ResultSet rs = DataBaseService.buildSelect("select " + tSpatialColumn + " from " + streetTable
							+ " where " + "upper("+streetColumn+")" + " LIKE '%" + street.trim() + "%'");
					try {
						geom.clear();
						while (rs.next()) {
							Geometry geometry = new Geometry();
							geometry.setWKT(rs.getString(1));
							geom.add(geometry);
						}

					} catch (SQLException e) {
						e.printStackTrace();
					}

				} else {
					ResultSet rs = DataBaseService.buildSelect("select " + tSpatialColumn + " from " + lotTable
							+ " where " + lotColumn1 + " LIKE '%" + street.trim() + "%'" + " and " + "cast("+lotColumn3+" as text)"
							+ " LIKE '%" + "" + number.trim() + "%'" + " order by " + lotColumn3);
					try {
						geom.clear();
						while (rs.next()) {
							Geometry geometry = new Geometry();
							geometry.setWKT(rs.getString(1));
							geom.add(geometry);
						}

					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		} else {
			ResultSet rs = DataBaseService.buildSelect("select " + tSpatialColumn + " from " + lotTable + " where "
					+ lotColumn2 + "=" + "'" + searchText.trim() + "'");
			try {
				geom.clear();
				while (rs.next()) {
					Geometry geometry = new Geometry();
					geometry.setWKT(rs.getString("st_astext"));
					geom.add(geometry);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return geom;
	}

}
