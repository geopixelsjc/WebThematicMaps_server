package geopixel.service; 

import geopixel.enumeration.DataBaseTypeEnum;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseService {

	private static String URL;
	private static String driver;
	
	public static DataBase getPostgresParameters(){
		DataBase dataBase = new DataBase();
		dataBase.setHost("localhost");
		dataBase.setPort("5432");
		dataBase.setUser("postgres");
		dataBase.setPassword("postgres");
		//Teste
		//dataBase.setDatabase("Observatorio2");
		//Deploy
		dataBase.setDatabase("observatorio");
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

	public static ResultSet buildSelect(String sql,DataBase dataBase) throws IOException, SQLException {
		Connection conn = DataBaseService.connect(dataBase);
		ResultSet rs = null;
		try {
			Statement stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = stm.executeQuery(sql);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}
		return rs;
	}
	
	public static int buildInsert(String sql,Connection conn) throws IOException, SQLException {		
		int count=0;		
		try {
			//Somente select
			//Statement stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			//Para insert
			Statement stm = conn.createStatement();
			count = stm.executeUpdate(sql);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return count;
	}
	
	

}
