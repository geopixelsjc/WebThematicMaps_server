package geopixel.thematic;

import geopixel.service.DataBaseService;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Dao {
	
	/**
	 * Get all indicators available on Indicators Table in data base   
	 * @param attributeTable name o Indicators table
	 * @return a @link ResultSET containing all indicators (identification, name,unit,description and link to file)
	 * @throws IOException
	 * @throws SQLException
	 */
    public static ResultSet getIndicators(String attributeTable) throws IOException, SQLException {
        String sqlQuery = "select *  from "+ attributeTable;
        ResultSet resultSet = DataBaseService.buildSelect(sqlQuery, DataBaseService.getPostgresParameters());
        return resultSet;
    }
    
	/** 
	 * Get values of a specific indicator (attribute) and year.    
	 * @param attributeTable value table
	 * @param attributeName name of column key specifying the desired indicator
	 * @param geocode name of column of key to the feature associated
	 * @param year selected year for values
	 * @return a @link ResultSet with geocode and value columns
	 * @throws IOException
	 * @throws SQLException
	 */
    public static ResultSet getIndicatorsValues(String attributeTable, String attributeName, String geocode, String value, String year, String target, String targetYear) throws IOException, SQLException {
        String sqlQuery = "select " + geocode + " , " + value + " from "+ attributeTable +" as value " + 
        				" where value." + attributeName + " = '" + target + "' and value." + year + " = " + targetYear + 
        				" order by value." + value + ";";
        ResultSet resultSet = DataBaseService.buildSelect(sqlQuery, DataBaseService.getPostgresParameters());
        return resultSet;
    } 
    
	/**
	 * Executes a select from geometry table retriving geocode, feature name and geometry as a Json string.   
	 * @param featureTable name of feature table
	 * @param featureGeocode geocode column name (feature id)
	 * @param featureName name of this feature
	 * @param featureGeometry column name of geometry, normally the_geom or geom
	 * @return a result set with tree columns (geocode,name, Json geometry)
	 * @throws IOException
	 * @throws SQLException
	 */
    public static ResultSet getGeometries (String featureTable, String featureGeocode,String featureName, String featureGeometry)
    		throws IOException, SQLException {
    	String sqlQuery = "SELECT "
    			+ featureGeocode 
    			+ ","
    			+ featureName
    			+","
    			+ "ST_AsGeoJSON(" + featureGeometry + ")::json As geometry" 
    			+" from "
    			+featureTable;
    	
    	ResultSet rs=DataBaseService.buildSelect(sqlQuery, DataBaseService.getPostgresParameters());
    	return rs;
    }
    
    public static int insertRow(Connection conn,String table,String geocode, String cityname,String indicator,String year,String value)
    		throws IOException, SQLException {
   	 int count=0;    	
   	 String sqlQuery = "INSERT INTO "
    			+ table 
    			+ " VALUES ( "     			
    			+geocode
    			+","
    			+cityname     			
    			+","
    			+indicator
    			+","     			
    			+year
    			+","     			
    			+value
    			+")";   	     			
   	
		count=DataBaseService.buildInsert(sqlQuery, conn);
	
	
    	
   	 return count;
   }
}
        
/*       
	public static ResultSet getCityGeoJSON() throws IOException, SQLException{
                
                String sqlQuery = "SELECT " +
                                "data.id, " +
                                "data.cd_geocod_uf, " +
                                "data.nm_uf, " +
                                "data.nm_uf_sigla, " +
                                "data.cd_geocodigo, " +
                                "data.nm_mun_2013, " +
                                "data.area_2013_km2, " +
                                "geometry.gid, " +
                                "geometry.id, " +
                                "geometry.nm_municip, " +
                                "geometry.geom " +
                                "FROM " +
                                "city_geometry geometry, " +
                                "city_information data " +
                                "WHERE data.cd_geocodigo = CAST(geometry.cd_geocodm AS bigint) " +
                                "LIMIT 3;";
                
                ResultSet resultSet = DataBaseService.buildSelect(sqlQuery, DataBaseService.getPostgresParameters());
                
                return resultSet;                
        }       
        
        
        public static ResultSet getFakeQuantil() throws IOException, SQLException{
                //String sqlQuery = "select * from city_information where nm_uf_sigla = 'SP' order by area_2013_km2;";                
                
                String sqlQuery = "SELECT row_to_json(fc) from "
                + "("
                + "    SELECT 'FeatureCollection' As type, array_to_json(array_agg(f)) As features from"
                + "    ( "
                + "            SELECT 'Feature' As type,"
                + "            ST_AsGeoJSON(geometry.geom)::json As geometry,"
                + "            row_to_json("
                + "                (SELECT r FROM "
                + "                    (SELECT data.id,"
                + "                        data.cd_geocod_uf,"
                + "                        data.nm_uf,"
                + "                        data.nm_uf_sigla,"
                + "                        data.cd_geocodigo,"
                + "                        data.nm_mun_2013,"
                + "                        data.area_2013_km2,"
                + "                        geometry.gid,"
                + "                        geometry.id,"
                + "                        geometry.nm_municip"
                + "                    ) r"
                + "                )"
                + "            ) As properties"
                + "        FROM"
                + "            city_geometry geometry,"
                + "            city_information data"
                + "        WHERE data.cd_geocodigo = CAST(geometry.cd_geocodm AS bigint)"
                //+ "        LIMIT 1"
                + "    ) as f"
                + ") as fc";
                
                ResultSet  resultSet = DataBaseService.buildSelect(sqlQuery, DataBaseService.getPostgresParameters());
                return resultSet;                
        } 
*/
 /*   String sqlQuery = "SELECT row_to_json(fc) from "
            + "("
            + "    SELECT 'FeatureCollection' As type, array_to_json(array_agg(f)) As features from"
            + "    ( "
            + "            SELECT 'Feature' As type,"
            + "            ST_AsGeoJSON(geometry.geom)::json As geometry,"
            + "            row_to_json("
            + "                (SELECT r FROM "
            + "                    (SELECT data.id,"
            + "                        data.cd_geocod_uf,"
            + "                        data.nm_uf,"
            + "                        data.nm_uf_sigla,"
            + "                        data.cd_geocodigo,"
            + "                        data.nm_mun_2013,"
            + "                        data.area_2013_km2,"
            + "                        geometry.gid,"
            + "                        geometry.id,"
            + "                        geometry.nm_municip"
            + "                    ) r"
            + "                )"
            + "            ) As properties"
            + "        FROM"
            + "            city_geometry geometry,"
            + "            city_information data"
            + "        WHERE data.cd_geocodigo = CAST(geometry.cd_geocodm AS bigint)"
            //+ "        LIMIT 1"
            + "    ) as f"
            + ") as fc";	
            */

