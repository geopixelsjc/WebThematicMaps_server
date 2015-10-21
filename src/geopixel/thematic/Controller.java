package geopixel.thematic;

import geopixel.model.external.ChoroplethMapDescription;
import geopixel.model.external.GeoJsonChoroplethMap;
import geopixel.model.external.JSonUtils;

import java.awt.Color;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeSet;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Controller {
	
		
	/**
	 * Retrieves all indicators (attributes) available to create choropleth maps in data base	
	 * @param table table that contains the indicators names and description
	 * @return a string as a JSON array containing identification, name, unit, description and link to a file.
	 * @throws SQLException
	 * @throws IOException
	 */
	public static String getIndicators(String table)throws SQLException, IOException {
				    	    	
	    ResultSet indicators = Dao.getIndicators(table);

		return JSonUtils.resultSet2Json(indicators);
	}
	
	/*
	public static String getChoroplethSchema(ChoroplethMap map){
		String schemaJson = "";
		return schemaJson;
	}
	*/
	/**
	 * Create a Choropleth map as a JSON given a choropleth map definition
	 * <p>
	 * To create the map the geometries are retrieved from a geometric feature table in data base,
	 * and the specified attributes values are retrieved from an attribute table in data base. 
	 * The number of attributes values could be greater than geometries. The geometries and attributes are
	 * paired using a HasMaph with the geocode key.
	 * <p>
	 * To create the color schema the attributes are retrieved from data base, and manipulated to generate 
	 * the ranges of colors depending on type of grouping.
	 * <p>
	 * The color palette is generated interpolating the given first and last colors. 
	 * 	
	 * @param map choropleth map definition
	 * @return a string with a GeoJson map
	 * @throws SQLException
	 * @throws IOException
	 */
	public static GeoJsonChoroplethMap getChoroplethMap(ChoroplethMapDescription map)throws SQLException, IOException{
		GeoJsonChoroplethMap mapGeoJson = new GeoJsonChoroplethMap ();
		List <Double> ranges = Controller.calculateRanges(map);
		List<String> colors = Controller.generateColors(ranges.size(),map.getFirstColor(), map.getLastColor());
		//List <String> features = new ArrayList<String>();
		String features = "";
	
		//Get attributes
		HashMap<String,Double> values=Controller.getAttributes(map);
		// Get geometries
		ResultSet rs = Controller.getGeometries(map);
		// Join attributes and geometry and assign color
		rs.next();
		while (!rs.isAfterLast()){
			String geocode = rs.getString(1);
			
			//System.out.println(geocode);
			
			String name = rs.getString(2);
			Double value = values.get(geocode);
			String color ="#ffffff";
			List <String> properties = new ArrayList<String>();
			int i=0;
			
			if (value != null){
				for(i=0;i<ranges.size()-1;i++){
					if ((value >= ranges.get(i))&& (value < ranges.get(i+1))){
						break;
					} 
				}
				if (i==ranges.size()-1){
					i--;
				}
				color=colors.get(i);
			} else {
				color = "#ffffff";
			}
			properties.add("geocode");
			properties.add(geocode);
			properties.add("name");
			properties.add(name);
			properties.add("value");
			if (value == null){
				properties.add("0.0");
				value = 0.0;
			}else{
				properties.add(Double.toString(value));
			}
			properties.add("color");
			properties.add(color);
							
			String jsonProperties=JSonUtils.createJsonProperties(properties);
			String jsonGeometry=rs.getString(3);
			String feature=JSonUtils.createJsonFeature(jsonGeometry, jsonProperties);			
			features=JSonUtils.addArrayItem(features, feature);
						
			rs.next();			
		}
		
		features=JSonUtils.addArrayItem(features, "");
		String j = JSonUtils.createGeoJson(features,map.getAttribute(),map.getCRS());		
		mapGeoJson.setMap(j);
		String l=Controller.createLegend(ranges,map.getFirstColor(),map.getLastColor());
		mapGeoJson.setLegend(l);
		return mapGeoJson;
	}
	
	/**
	 * Retrieves the geometries of the choropleth map given a map description
	 * @param map map description parameters
	 * @return a @link ResultSet containing geocode (key of feature), feature name and geometry as a Json string.
	 * @throws SQLException
	 * @throws IOException
	 */
	public static ResultSet getGeometries (ChoroplethMapDescription map)throws SQLException, IOException {;
		ResultSet rs = Dao.getGeometries(map.getLayer(),map.getFeatureCode(),map.getFeatureName(),map.getGeometry());
		return rs;
	}

	/**
	 * Retrieve the attributes values from a specific table and attribute in data base and the associated feature code, a link to geometry    
	 * @param map description parameters of choropleth map
	 * @return a hash map with feature codes (key) and attribute values pairs
	 * @throws SQLException
	 * @throws IOException
	 */
    public static HashMap<String,Double> getAttributes(ChoroplethMapDescription map) throws SQLException, IOException {
    	String table= map.getAttributeTable();
    	String indicator = map.getAttribute();
    	String geocode = map. getGeoCode();
    	String year = map.getYear();
    	String targetIndicator = map.getTargetAttribute();
    	String targetYear = map.getTargetYear();
    	String val = map.getValue();
        ResultSet attributesResultSet = Dao.getIndicatorsValues(table, indicator, geocode, val, year, targetIndicator, targetYear);
        HashMap<String,Double> values = new HashMap<String,Double> ();
               
        while (attributesResultSet.next()) {
                String code = attributesResultSet.getString(1);
                Double value = attributesResultSet.getDouble(2);
                values.put(code,value);
        }
        
         return values;
    }
    
	/**
	 * Create a Json string with choropleth legend data    
	 * @param ranges list of value breaks from minimum to maximum
	 * @param firstColor color of first class
	 * @param lastColor color of last class
	 * @return a string as Json array for each legend item with pairs for color, class name, minimum value, maximum value and hits
	 */
    public static String createLegend(List<Double> ranges,String firstColor, String lastColor){
    	String legendItem;
    	String legend ="[";
    	List<String> colors = Controller.generateColors(ranges.size()-1,firstColor, lastColor);
    	for(int i=0; i < ranges.size()-1;i++){
    		legendItem="{\"Color\":\"";
       		legendItem=legendItem+colors.get(i);
       		legendItem=legendItem+"\",\"Class\":\"";
       		legendItem=legendItem+Integer.toString(i+1);
       		legendItem=legendItem+"\",\"MinValue\":\"";
    		legendItem=legendItem+ranges.get(i);
    		legendItem=legendItem+"\",\"MaxValue\":\"";
    		legendItem=legendItem+ranges.get(i+1);
    		legendItem=legendItem+"\",\"Hits\":";
    		legendItem=legendItem+("\" \"}");
    		if (i<(ranges.size()-2))legendItem=legendItem+",";
    		legend=legend+legendItem;
    	}
    	legend=legend+"]";
    	return legend; 
    	 
    } 
    
	/**
	 * Creates a set of opaque Colors from given first and last colors. Colors are represented by its hexadecimal codes
	 * The colors are created interpolating linearly the RGB components of last and first colors.
	 * @param nColors number of colors
	 * @param firstColor first given color, as a hexadecimal String (#rrggbb)
	 * @param lastColor last given color, as a hexadecimal string
	 * @return list of hexadecimal strings representing the colors.
	 */
    public static List<String> generateColors(int nColors,String firstColor, String lastColor) {
            List<String> colors = new ArrayList<String>();            
            Color first = Color.decode(firstColor);
            Color last = Color.decode(lastColor);
            
            colors.add(firstColor);
            if (nColors > 2) {
            	int firstR =first.getRed();
            	int firstG =first.getGreen();
            	int firstB =first.getBlue();
            	int lastR =last.getRed();
            	int lastG =last.getGreen();
	            int lastB =last.getBlue();
	            int stepR=((lastR-firstR)/nColors);
	            int stepG=((lastG-firstG)/nColors);
	            int stepB=((lastB-firstB)/nColors);
	            Color c= new Color(firstR,firstG,firstB);
            	for (int i=1; i<nColors-1; i++){
            		c= new Color(c.getRed()+ stepR,c.getGreen()+ stepG,c.getBlue()+ stepB);
            		String hex = String.format("#%02x%02x%02x", c.getRed(), c.getGreen(), c.getBlue());
            		colors.add(hex);     	
               	}          	
            }
            colors.add(lastColor);
            return colors;
    }

	/**
	* Calculates the quantiles or step ranges
	* @param map object with choropleth definitions (table, target attribute,number of classes, 
	* type of grouping (quantiles, slices, uniquevalues)
	* <p>
	* Select target attribute in a record set ordered by values with geocode and value.
	* @return list of attribute value interval limits begining at minimum value and ending at maximum value
	 * @throws SQLException 
	 * @throws IOException 
	*/
    public static List<Double> calculateRanges(ChoroplethMapDescription map) throws IOException, SQLException {
            int breaks = Integer.valueOf(map.getNClasses());
            List<Double> ranges = new ArrayList<Double>();
            ResultSet rs = Dao.getIndicatorsValues(map.getAttributeTable(), map.getAttribute(),map.getGeoCode(), map.getValue(), map.getYear(), map.getTargetAttribute(), map.getTargetYear());
            rs.last();
            int size=rs.getRow();
            
            Double maximun = rs.getDouble(2);
            int step = 1;
            switch (map.getGroupingType()){
            	case "quantiles": {
            		rs.first();
            		int breaksize=size/breaks;            		
            		for (int i=0;i < breaks; i++ ){
            			ranges.add(rs.getDouble(2));
            			step += breaksize;  
            			rs.absolute(step);
            		}
            		ranges.add(maximun);
            		break;
            	}
            	case "slices":{
            		rs.first();
                    Double minimun = rs.getDouble(2);           		
            		ranges.add(minimun);
            		Double interval = minimun;
            		Double breakValues = (maximun-minimun)/breaks;
            		for (int i=1;i < breaks; i++ ){
            			ranges.add(interval += breakValues);            			        	
            		}
            		ranges.add(maximun);
            		break;
                }
            	case "uniquevalues" :{
            		//TBD
            		break;
            	}
            	default:
            		break;             		
            }
            return ranges;
    }
}