package geopixel.service; 

import geopixel.enumeration.DataBaseTypeEnum;
import geopixel.model.external.GenericTable;
import geopixel.model.hb.dto.AppDicionarioDado;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public class DataBaseService {

	private static String URL;
	private static String driver;
	
	public static DataBase getPostgresParameters(){
		DataBase dataBase = new DataBase();
		dataBase.setHost("localhost");
		dataBase.setPort("5432");
		dataBase.setUser("postgres");
		dataBase.setPassword("123");
		dataBase.setDatabase("geo");
		dataBase.setDataBaseTypeEnum(DataBaseTypeEnum.POSTGRES);
		return dataBase;
	}

	public static Connection connect(DataBase dataBase) throws IOException {
		DataBaseTypeEnum dbType = dataBase.getDataBaseTypeEnum();
		Connection conn = null;
		
		switch (dbType) {
		case POSTGRES:
			 URL = "jdbc:postgresql://" + dataBase.getHost() + ":" + dataBase.getPort() + "/" + dataBase.getDatabase();
			 driver = "org.postgresql.Driver";
			break;
		case SQLSERVER:
			break;
			
		case MYSQL:
			
			break;
			
		case ORACLE:
			
			break;

		default:
			break;
		}
		
		String user = dataBase.getUser();
		String password = dataBase.getPassword();
		
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(URL,user,password);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return conn;
	}

	public static ResultSet buildSelect(String sql,DataBase dataBase) throws IOException {
		Connection conn = DataBaseService.connect(dataBase);
		ResultSet rs = null;
		try {
			Statement stm = conn.createStatement();
			rs = stm.executeQuery(sql);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
		}
		return rs;
	}
	
	public static GenericTable getExternalTable(DataBase dataBase, String tableName, int limit, int offSet, ArrayList<AppDicionarioDado> dicionarioDados) throws IOException, SQLException{
		ArrayList<String> data = null;
		GenericTable table = null; 
		String collumns ="";
		boolean isOnlyCollumn = false;
		StringBuilder auxCollumns = null;
		String SQLcollums = null; 
		
		for (AppDicionarioDado dicionarioDado : dicionarioDados) {
			
			if(dicionarioDados.size()<=1){
				collumns += dicionarioDado.getAtributo();
				isOnlyCollumn = true;
				SQLcollums = collumns;
			}
			else
				collumns += dicionarioDado.getAtributo().concat(",");
		}
		
		if(!isOnlyCollumn){
			//remove ultima virgula.
			int lastCharIndex = collumns.length()-1;
			auxCollumns = new StringBuilder(collumns);
			auxCollumns.setCharAt(lastCharIndex,' ');
			SQLcollums = auxCollumns.toString();
		}
		
		ResultSet rs = DataBaseService.buildSelect("SELECT "+SQLcollums+" FROM "+ "\"" + tableName+ "\" LIMIT "+limit+" OFFSET "+offSet, dataBase);
	
		int colSize = rs.getMetaData().getColumnCount();
		int jcount = colSize; 
		
		data = new ArrayList<String>();
		StringBuilder jsn = null;
		
		String json="[{";

		while (rs.next()) {
			for (int j = 0; j < colSize; j++) {
				json += " \""+rs.getMetaData().getColumnLabel(j+1)+"\":"+"\""+String.valueOf(rs.getObject(j+1))+"\",";
				
				if(j==(jcount-1)){
					int jsIndex = json.length()-1;
					jsn = new StringBuilder(json);
					jsn.setCharAt(jsIndex,' ');
					
					json=jsn.toString();
					json+="},{";
				}
			}
		}
			int jsIndex = json.length()-1;
			StringBuilder js = new StringBuilder(json);
			js.setCharAt(jsIndex,' ');
			
			json = js.toString();
			
			int idx = json.length()-2;
			StringBuilder j = new StringBuilder(json);
			j.setCharAt(idx,' ');
			
			table = new GenericTable();
			table.setName(tableName);
			table.setJson(j.toString()+"]");
		
		return table;
	}

}
