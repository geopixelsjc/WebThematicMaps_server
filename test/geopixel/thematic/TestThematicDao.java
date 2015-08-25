/**
 * 
 */
package geopixel.thematic;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.junit.Test;

/**
 * @author PauloLuan
 *
 */
public class TestThematicDao {
        
        @Test
        public void testCalculateRanges() throws IOException, SQLException, JSONException {
                ResultSet resultSet = Dao.getFakeQuantil();
                assertNotNull(resultSet);
                
                while (resultSet.next()) {
                        String jsonString = resultSet.getString(1);
                        
                        JSONObject obj = new JSONObject(jsonString);
                        assertNotNull(obj);
                        
                        JSONArray features = obj.getJSONArray("features");
                        
                        for (int i = 0; i < features.length(); i++) {
                                JSONObject feature = features.getJSONObject(i);
                                JSONObject properties = feature.getJSONObject("properties");
                                Double area = properties.getDouble("area_2013_km2");
                                assertNotNull(area);
                                System.out.println(area);
                        }
                        
                }
        }
        
        @Test
        public void testSelectAreas() throws IOException, SQLException, JSONException {
                ResultSet resultSet = Dao.getAreas();
                assertNotNull(resultSet);
        }
}
