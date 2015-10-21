package geopixel.service;
 
import geopixel.model.geolocation.Endereco;
import geopixel.model.geolocation.Geometry;
import geopixel.model.hb.dto.AppDicionarioDado;
import geopixel.model.hb.dto.AppPermissao;
import geopixel.model.hb.dto.AppTabela;
import geopixel.model.hb.dto.AppTema;
import geopixel.model.legacy.bo.AcessoBo;
import geopixel.model.legacy.dto.Acesso;
import geopixel.model.legacy.dto.Funcao;
import geopixel.model.legacy.dto.Perfil;
import geopixel.utils.Cryptography;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.crypto.NoSuchPaddingException;
import javax.swing.JOptionPane;

public class TerracoreService {

	private static String streetTable;
	private static String lotTable;
	private static String lotColumn1;
	private static String lotColumn2;
	private static String lotColumn3;
	
	private static String streetColumn;
	private static String tSpatialColumn;
	
	/**
	 * 
	 * @throws IOException
	 */
	public TerracoreService() throws IOException {
		/**
		 * TODO: xml de propiedades
		 */
		lotTable = "geo_lote";//PropertiesReader.getSearchProp().getProperty("prop.search.lotTable");
		lotColumn1 = "chave";//PropertiesReader.getSearchProp().getProperty("prop.search.lotColumn1");
		lotColumn2 = "chave";//PropertiesReader.getSearchProp().getProperty("prop.search.lotColumn2");
		lotColumn3 = "chave";//PropertiesReader.getSearchProp().getProperty("prop.search.lotColumn3");
		
		streetTable = "geo_eixo";//PropertiesReader.getSearchProp().getProperty("prop.search.streetTable");
		streetColumn = "nome";//PropertiesReader.getSearchProp().getProperty("prop.search.streetColumn");
	}
	
	public static ArrayList<AppPermissao> getUserPermission(int theme_id) throws IOException, SQLException{
		ArrayList<AppPermissao> permissaos = new ArrayList<AppPermissao>();
		String sql = "SELECT pm.pem_id, pm.permit, pm.resume "+
					 "FROM app_tema t,app_permissao pm "+  

					 "where t.tma_id = pm.tma_id "+ 
					 "and t.tma_id =" + theme_id;
		
		ResultSet rs = DataBaseService.buildSelect(sql,DataBaseService.getPostgresParameters());
		while(rs.next()){
			AppPermissao permissao = new AppPermissao();
			permissao.setPemId(rs.getInt(1));
			permissao.setPermit(rs.getString(2));
			permissao.setResume(rs.getInt(3));
			permissaos.add(permissao);
		}
		
		return permissaos;
	}
	
	public static ArrayList<AppTabela> getUserTablesByPermissions(ArrayList<AppPermissao> permissaos) throws IOException, SQLException{
		
		ArrayList<AppTabela> tabelas = new ArrayList<AppTabela>();
		
		String sql = "select tb.tbl_id, tb.nome, tb.url_conexao_banco "+
		"from app_permissao pm, app_dicionario_dado dd, app_tabela tb "+
		"where tb.tbl_id = dd.tbl_id "+
		"and dd.dd_id = pm.dd_id "+
		"and pm.pem_id ="; 
		
		for (AppPermissao permissao : permissaos) {
			
			String permitType = permissao.getPermit();
			int resume = permissao.getResume();
			
			if((permitType.equals("READ") || permitType.equals("WRITE")) && resume>0){
				
				JOptionPane.showMessageDialog(null,resume);
				
				String sqlp = sql.concat(String.valueOf(permissao.getPemId()));
				ResultSet rs = DataBaseService.buildSelect(sqlp,DataBaseService.getPostgresParameters());
				
				while(rs.next()){
					AppTabela tabela = new AppTabela();
					tabela.setTblId(rs.getInt(1));
					tabela.setNome(rs.getString(2));
					tabela.setUrlConexaoBanco(rs.getString(3));
					tabelas.add(tabela);
				}
			}
		}
		
		return tabelas;
	}
	
	
	public static ArrayList<AppTabela> getUserTableByThemeId(int theme_id) throws IOException, SQLException{
		ArrayList<AppPermissao> permissaos = TerracoreService.getUserPermission(theme_id);
		ArrayList<AppTabela> tabelas = TerracoreService.getUserTablesByPermissions(permissaos);
		
		return tabelas;
	}
	
//	public static GenericTable getPhysicalTable(AppTabela tabela, int limit, int offset) throws IOException, SQLException{
//		String con = tabela.getUrlConexaoBanco();
//		//todo:programacao defensiva
//		String [] param = con.split(";");
//				
//		DataBase dataBase = new DataBase();
//		dataBase.setHost(param[0]);
//		dataBase.setPort(param[1]);
//		dataBase.setDatabase(param[2]);
//		dataBase.setUser(param[3]);
//		dataBase.setPassword(param[4]);
//		// implemetar case p outros bancos
//		dataBase.setDataBaseTypeEnum(DataBaseTypeEnum.POSTGRES);
//		
//		ArrayList<AppDicionarioDado> dicionarioDados = TerracoreService.getTableAttributesDD(tabela);
//		
//		return DataBaseService.getExternalTable(dataBase,tabela.getNome(),limit,offset,dicionarioDados);
//	}
	
	public static ArrayList<AppDicionarioDado> getTableAttributesDD(AppTabela tabela) throws IOException, SQLException{
		int id = tabela.getTblId();
		
		ArrayList<AppDicionarioDado> dados = new ArrayList<AppDicionarioDado>();
		
		String sql = "select dd.dd_id, dd.atributo, dd.pesquisa "+
		"from app_dicionario_dado dd, app_tabela tb "+
		"where tb.tbl_id = dd.tbl_id "+
		"and tb.tbl_id =" + id;
		
		ResultSet rs = DataBaseService.buildSelect(sql,DataBaseService.getPostgresParameters());
		
		while(rs.next()){
			AppDicionarioDado dicionarioDado = new AppDicionarioDado();
			dicionarioDado.setDdId(rs.getInt(1));
			dicionarioDado.setAtributo(rs.getString(2));
			dicionarioDado.setPesquisa(rs.getBoolean(3));
			dados.add(dicionarioDado);
		}
		
		return dados;
	}
	
//	/**
//	 * 
//	 * @param key
//	 * @param table_id
//	 * @return
//	 * @throws IOException
//	 * @throws SQLException
//	 */
//	public static ArrayList<AppDicionarioDado> getUserDataDictionaries(String key, int theme_id) throws IOException, SQLException{
//		ArrayList<AppDicionarioDado> dicionarioDados = new ArrayList<AppDicionarioDado>();
//		
//		String sql = "SELECT pm.pem_id, pm.queried, pm.permit, pm.resume, dd.dd_id, dd.atributo, dd.read_only, dd.write_only, dd.read_write "+
//					 "FROM app_acesso a, app_usuario u, app_usarioxprefil up, app_perfil p,app_tema t, app_camada c, "+ 
//					 "app_permissao pm ,app_dicionario_dado dd, app_tabela tb "+
//
//						"where a.usr_id = u.usr_id "+
//						"and u.usr_id = up.usr_id "+
//						"and up.prf_id = p.prf_id "+
//						"and p.prf_id = t.prf_id "+
//						"and c.cmd_id = t.cmd_id "+
//						"and t.tma_id = pm.tma_id "+
//						"and pm.pem_id = dd.pem_id "+
//						"and tb.tbl_id = dd.tbl_id "+
//						"and a.chave = '"+key+"' "+//61fba0891b737bff308badbe119e0160'
//						"and t.tma_id="+theme_id;
//		
//		ResultSet rs = DataBaseService.buildSelect(sql,DataBaseService.getPostgresParameters());
//		
//		while(rs.next()){
//			AppPermissao permissao = new AppPermissao();
//			AppDicionarioDado dicionarioDado = new AppDicionarioDado();
//			permissao.setPemId(rs.getInt(1));
//			permissao.setQueried(rs.getBoolean(2));
//			permissao.setPermit(rs.getString(3));
//			permissao.setResume(rs.getInt(4));
//			
//			dicionarioDado.setDdId(rs.getInt(5));
//			dicionarioDado.setAtributo(rs.getString(6));
//			dicionarioDado.setReadOnly(rs.getBoolean(7));
//			dicionarioDado.setWriteOnly(rs.getBoolean(8));
//			dicionarioDado.setReadWrite(rs.getBoolean(9));
//			
//			dicionarioDado.setAppPermissao(permissao);
//			
//			dicionarioDados.add(dicionarioDado);
//		}
//							
//		return dicionarioDados;
//	}

	
	/**
	 * 
	 * @param key
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 */
	public static ArrayList<AppTema> getThemesByKey(String key) throws IOException, SQLException{
		
		ArrayList<AppTema> temas = new ArrayList<AppTema>();
				
		String sqltheme = "select t.tma_id, t.nome_tema, t.titulo_tema, t.descricao "+ 
				"from app_acesso a, app_usuario u, app_usarioxprefil up, app_perfil p, app_tema t "+
				"where a.usr_id = u.usr_id "+
				"and u.usr_id = up.usr_id "+
				"and up.prf_id = p.prf_id "+
				"and p.prf_id = t.prf_id "+
				"and a.chave ='"+key+"'";
		
		ResultSet rst = DataBaseService.buildSelect(sqltheme, DataBaseService.getPostgresParameters());
		
		while (rst.next()){
			AppTema tema = new AppTema();
			tema.setTmaId(rst.getInt(1));
			tema.setNomeTema(rst.getString(2));
			tema.setTituloTema(rst.getString(3));
			tema.setDescricao(rst.getString(4));
			temas.add(tema);
		}
		
		return temas;
		
	}
	
	public static Funcao getUserFunctions(){
		
		return null;
	}
	
	public static Perfil getUserProfile(){
		
		return null;
	}
	
	public static boolean checkKey(String key) throws IOException, SQLException{
		boolean valid = false;
		String sql = "select * from app_acesso where chave = '"+key+"'";
		ResultSet rs = DataBaseService.buildSelect(sql,DataBaseService.getPostgresParameters());
		valid = rs.next();
		return valid;
	}
	
	public static Acesso login(String login, String password) throws IOException, SQLException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException{
		boolean valid = false;
		Acesso acesso = new Acesso();	
		
		String sql = "select * from app_acesso where login = '"+login+"' and senha = '"+password+"'";
		ResultSet rs = DataBaseService.buildSelect(sql,DataBaseService.getPostgresParameters());
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
	 * @throws SQLException 
	 */
	public static ArrayList<Endereco> execAlphaNumericLocation(String search) throws IOException, SQLException {
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
							+ streetColumn,DataBaseService.getPostgresParameters());
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
							+ lotColumn3,DataBaseService.getPostgresParameters());

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
						+ "%'",DataBaseService.getPostgresParameters());
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
	 * @throws SQLException 
	 */
	public static ArrayList<Geometry> execGeoLocation(String search) throws IOException, SQLException {
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
							+ " where " + "upper("+streetColumn+")" + " LIKE '%" + street.trim() + "%'",DataBaseService.getPostgresParameters());
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
							+ " LIKE '%" + "" + number.trim() + "%'" + " order by " + lotColumn3,DataBaseService.getPostgresParameters());
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
					+ lotColumn2 + "=" + "'" + searchText.trim() + "'",DataBaseService.getPostgresParameters());
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
