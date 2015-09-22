package geopixel.utils; 

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesReader {
	
	public static Properties getDBProp() throws IOException {
		Properties props = new Properties(); 
		FileInputStream file = new FileInputStream("../terracore-server-piracicaba/DataBase.properties"); 
		props.load(file); 
		return props; 
	}
	
	public static Properties getSearchProp() throws IOException {
		Properties props = new Properties(); 
		FileInputStream file = new FileInputStream("../terracore-server-piracicaba/Search.properties"); 
		props.load(file); 
		return props; 
	}
}


