package geopixel.service;
 
import geopixel.model.geolocation.Endereco;
import geopixel.model.geolocation.Geometry;
import geopixel.utils.PropertiesReader;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
