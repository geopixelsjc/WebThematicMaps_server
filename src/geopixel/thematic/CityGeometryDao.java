package geopixel.thematic;

import javax.persistence.EntityManager;

import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;


public class CityGeometryDao {
        
        private static EntityManager em;
        
        public CityGeometryDao() {
                //em = EntityManagerUtil.getEntityManagerFactory().createEntityManager();
        }
        
        public static void getCityGeometry() {
                /*String stringQuery = "SELECT " +
                                "data.id, " +
                                "data.cd_geocod_uf, " +
                                "data.nm_uf, " +
                                "data.nm_uf_sigla, " +
                                "data.cd_geocodigo, " +
                                "data.nm_mun_2013," +
                                "data.area_2013_km2," +
                                "geometry.gid, " +
                                "geometry.id, " +
                                "geometry.nm_municip, " +
                                "geometry.geom" +
                                "FROM " +
                                "city_geometry geometry," +
                                "city_information data" +
                                "WHERE data.cd_geocodigo = CAST(geometry.cd_geocodm AS bigint)" +
                                "LIMIT 3";*/
                
                String stringQuery = "SELECT gid, id, cd_geocodm, nm_municip FROM city_geometry";
                
                Session session = em.unwrap(Session.class);
                
                org.hibernate.Query query = session.createQuery(stringQuery);
                query.setReadOnly(true);
                query.setFetchSize(Integer.MIN_VALUE);
                ScrollableResults results = query.scroll(ScrollMode.FORWARD_ONLY);
                
                // iterate over results
                while (results.next()) {
                        Object row = results.get();
                        System.out.println(row);
                }
                
                results.close();
                
        }
}