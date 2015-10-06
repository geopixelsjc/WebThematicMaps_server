package geopixel.model.external;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;
/**
 * Class to create Json and GeoJson from different sources : result sets, result set rows, arrays...
 * @author Freitas,U.M.
 *
 */
public class JSonUtils {
	
	private String json = "";
	
	public void setJson(String js) {json = js;}
	
	public String getJson () {return json;}
	
	/**
	 * Creates a Json from  a SQL ResultSET 	
	 * @param rs the result set 
	 * @return a Json with all rows and attributes in result set
	 * @throws SQLException
	 */
	public static String resultSet2Json(ResultSet rs) throws SQLException{
		ResultSetMetaData md;
		md = rs.getMetaData();
		int ncolumns = md.getColumnCount();
		String json = "{";
		for (int i = 1; i < ncolumns; i++ ) {
				json = json +"\"" + md.getColumnName(i) + "\":\"" + rs.getString(i)+ "\",";			
		}
		json = json + "\"" + md.getColumnName(ncolumns) + "\":\"" + rs.getString(ncolumns) + "\"}";
		return json;			
	}
	
	/**
	 * Encapsulates a an array of Json features as a GeoJson	
	 * @param json a Json array of Json features
	 * @param name name Geojson
	 * @param crs EPSG number of  cartography projection
	 * @return a GeoJson string
	 */
	public static String createGeoJson(String json, String name,String crs){
		String geoJson = "{\"name\":\""+ name +"\",\"type\":\"FeatureCollection\"";
		geoJson = geoJson + ",\"crs\":{\"type\":\"name\",\"properties\":{\"name\":\"EPSG:" + crs + "\"}}";
		geoJson = geoJson + ",\"features\":"+json+"}";
		return geoJson;
	
	}
	
	/**
	 * Creates a Json feature from a Json geometry and Json properties
	 * @param geometry Json geometry
	 * @param properties Json properties
	 * @return Json feature
	 */
	public static String createJsonFeature(String geometry, String properties){
		String feature = "{\"type\":\"Feature\"," + "\"geometry\":" +geometry;
		feature = feature + ","+ properties+"}";
		return feature;
	}
	
	/**
	 * creates a Json properties from a list	
	 * @param pairs a list of pairs (name, property value)
	 * @return a Json properties 
	 */
	public static String createJsonProperties (List <String> pairs){
		String p = "\"properties\":{";
		int i;
		for(i=0; i < pairs.size()-2;i+=2){
			p = p + "\"" + pairs.get(i) + "\":\"" + pairs.get(i+1)+ "\",";
		}
		p = p + "\"" + pairs.get(i) + "\":\"" + pairs.get(i+1)+ "\"}";
		return p;
	}
	
	/**
	 * Creates a Json array	
	 * @param json a list of Json elements
	 * @return the Json array
	 */
	public static String createJsonArray(List <String> json){
		String array = "[";
		int i;
		for (i=0;i<json.size()-1; i++){
			array = array + json.get(i) + ",";			
		}
		array = array +json.get(i)+ "]";
		return array;
	}
/**
 * Adds a Json item as an array item on an existing Json array, not closed, or an empty json string	
 * @param jsonArray the existing Json array or an empty string
 * @param jsonItem the Json array item, if empty the array will be closed inserting a ]
 * @return the updated Json array
 */
	public static String addArrayItem (String jsonArray, String jsonItem){
		if (jsonArray.length()==0) {
			jsonArray = "[";		
		}
		if (jsonItem.length() == 0){
			jsonArray=jsonArray+"]";
		} else {
			jsonArray=jsonArray+","+ jsonItem;
		}
		
		return jsonArray;
	}
	

}
