package geopixel.thematic;

import geopixel.service.DataBaseService;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Dao {
        
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
                
                ResultSet resultSet = DataBaseService.buildSelect(sqlQuery, DataBaseService.getPostgresParameters());
                return resultSet;                
        }   
        
        public static ResultSet getAreas() throws IOException, SQLException {
                String sqlQuery = "select area_2013_km2 from city_information where nm_uf_sigla = 'SP' order by area_2013_km2;";
                ResultSet resultSet = DataBaseService.buildSelect(sqlQuery, DataBaseService.getPostgresParameters());
                return resultSet;
        }
        
}
