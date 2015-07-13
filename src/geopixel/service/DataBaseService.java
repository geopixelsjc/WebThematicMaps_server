package geopixel.service; 

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseService {
	private static String host;
	private static String user;
	private static String password;
	private static String database;
	private static String port;
	private static Connection conn;
	private static Statement stm;
	private static ResultSet rs;

	public static Connection connect() throws IOException {
		
		host = "localhost";//PropertiesReader.getDBProp().getProperty("prop.db.host").toString();
		user = "postgres";//PropertiesReader.getDBProp().getProperty("prop.db.user").toString();
		password ="postgres";//PropertiesReader.getDBProp().getProperty("prop.db.password").toString();
		database ="piracicaba";//PropertiesReader.getDBProp().getProperty("prop.db.database").toString();
		port = "5432";//PropertiesReader.getDBProp().getProperty("prop.db.port").toString();
		
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection("jdbc:postgresql://" + host + ":" + port + "/" 
			+ database,user,password);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return conn;
	}

	public static ResultSet buildSelect(String sql) throws IOException {
		DataBaseService.connect();
		try {
			stm = conn.createStatement();
			rs = stm.executeQuery(sql);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return rs;
	}

}
