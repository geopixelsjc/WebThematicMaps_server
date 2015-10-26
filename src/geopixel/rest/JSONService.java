package geopixel.rest;
 
import geopixel.model.external.ChoroplethMapDescription;
import geopixel.model.external.GeoJsonChoroplethMap;
import geopixel.service.DataBase;
import geopixel.service.DataBaseService;
import geopixel.thematic.Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/json")
public class JSONService {
	
	 @GET
     @Path("/")
     @Produces(MediaType.APPLICATION_JSON)
     public Response thematicEndpoint() {
             String result = "/thematic";
             return Response.status(200).entity(result).build();
     }


     @GET
     @Path("/connect")
     @Produces(MediaType.APPLICATION_JSON)
     public Response Connect() {
     		DataBase db = new DataBase();
     		Connection conn = null;
     		db=DataBaseService.getPostgresParameters();
     		try {
					conn=DataBaseService.connect(db);
				} catch (IOException e) {
					e.printStackTrace();
				}        
     	                	
        return Response.status(200).entity(conn).build();
     }
     
     @GET
     @Path("/indicators")
     @Produces(MediaType.APPLICATION_JSON)
     public Response indicatorsEndpoint(
     		@QueryParam("table") String table,
     		@QueryParam("indicator")String indicator){
     	
     	String result = "";
     	
     	try {
				result = Controller.getIndicators(table,indicator);
			} catch (SQLException  | IOException e) {
				e.printStackTrace();
			} 
     	
        return Response.status(200).entity(result).build();
     }
     
     @GET
     @Path("/years")
     @Produces(MediaType.APPLICATION_JSON)
     public Response indicatorsEndpoint(
     		@QueryParam("table") String table,
     		@QueryParam("attribute") String attribute,
     		@QueryParam("value") String value, 
			@QueryParam("year")String year,
			@QueryParam("targetattribute")String targetAttribute){
				
		ChoroplethMapDescription map= new ChoroplethMapDescription();
		map.setAttributeTable(table);
		map.setAttribute(attribute);
		map.setValue(value);				
		map.setYear(year);
		map.setTargetAttribute(targetAttribute);

     	String result = "";
     	
     	try {
				result = Controller.getYears(map);
			} catch (SQLException  | IOException e) {
				e.printStackTrace();
			} 
     	
        return Response.status(200).entity(result).build();
     }
     
                     
/*
     @GET
     @Path("/choroplethschema")
     @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
     public String choroplethSchemaEndpoint(               		
     		@QueryParam("table") String table,
     		@QueryParam("attribute") String attribute,
     		@QueryParam("groupingtype") String groupingType,
     		@QueryParam("nclasses") String nClasses,
     		@QueryParam("firstcolor") String firstColor,
     		@QueryParam("lastcolor") String lastColor,
     		@QueryParam("year")String year){
     	
     	ChoroplethMap map= new ChoroplethMap();
     	map.setAttributeTable(table);
     	map.setTargetAttribute(attribute);
     	map.setGroupingType(groupingType);
     	map.setNClasses(nClasses);
     	map.setFirstColor(firstColor);
     	map.setLastColor(lastColor);
     	map.setYear(year);
     	
 		String schema;
 		
 		try {
 			schema = Controller.getChoroplethSchema(map);
 		} catch (IOException | SQLException e) {
 			((Throwable) e).printStackTrace();
 		}
  
            
             return schema;
     }
     */
	/**
	* Creates a Choropleth Map
	* @param table attribute table name
	* @param attribute attribute name which values will drive the map
	* @param geocode key to the associated geographic representation 
	* @param layer geographic representation
	* @param featureCode 
	* @param groupingType "quantiles", "unique value" or slices 
	* @param nclasses number of classes 
	* @param firstColor color of first class
	* @param lastColor color of last class, intermediated classes will use a linear interpolated color on RGB space between first and last colors 
	* @param year
	* @return GeoJson with a correspondent color for each feature
	*/
	@GET
	@Path("/choroplethmap")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public GeoJsonChoroplethMap ChoroplethMapEndpoint(
			@QueryParam("table") String table,
			@QueryParam("attribute") String attribute,
			@QueryParam("geocode") String geocode, 
			@QueryParam("value") String value, 
			@QueryParam("layer") String layer,
			@QueryParam("featurecode") String featureCode,
			@QueryParam("featurename") String featureName,
			@QueryParam("box")String box,
			@QueryParam("groupingtype") String groupingType,
			@QueryParam("nclasses")String nClasses,
			@QueryParam("firstcolor")String firstColor,
			@QueryParam("lastcolor")String lastColor,
			@QueryParam("year")String year,
			@QueryParam("targetyear")String targetYear,
			@QueryParam("targetattribute")String targetAttribute){
	
		ChoroplethMapDescription map= new ChoroplethMapDescription();
		map.setAttributeTable(table);
		map.setAttribute(attribute);
		map.setGeocode(geocode);
		map.setValue(value);
		map.setLayer(layer);
		map.setFeatureCode(featureCode);
		map.setFeatureName(featureName);
		map.setBox(box); //if null no box restriction      
		map.setGroupingType(groupingType);
		map.setNClasses(nClasses);
		map.setFirstColor(firstColor);
		map.setLastColor(lastColor);
		map.setYear(year);
		map.setTargetYear(targetYear);
		map.setTargetAttribute(targetAttribute);
	
		GeoJsonChoroplethMap geojson = new GeoJsonChoroplethMap(); 
		
		try {
			 geojson = Controller.getChoroplethMap(map);
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}
	    
	     return geojson;
	}
}