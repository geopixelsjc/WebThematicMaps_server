package geopixel.thematic;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public class Controller {
        
        public String calculateQuantil(
                                       Integer classesNumber,
                                       String attributeName,
                                       String attributeValue) throws IOException, SQLException {
                Dao.getCityGeoJSON();
                
                return null;
        }
        
        public void applyColorToGeoJSON(
                                        JSONObject geojson,
                                        String color,
                                        String attributeName,
                                        Double attributeValue) throws JSONException {
                // for no geojson e aplicar quando a area for menor que ...
                //                Double value = geojson.getDouble(attributeName);
                //                Double value2 = geojson.getDouble(attributeName);
                //                
                //                getColorForProperty(range, value);
                //                
                //                if (value < attributeValue) {
                //                        geojson.put("color", color);
                //                }
                
        }
        
        public void getColorForProperty(ArrayList<Double> ranges, Double value) {
                
                for (int i = 0; i < ranges.size(); i++) {
                        Double range1 = ranges.get(i);
                        Double range2 = ranges.get(i + 1);
                        
                }
                
        }
        
        public List<Double> calculateRanges(double[] values, int breaks) {
                int size = values.length;
                int breakSize = size / breaks;
                int count = 0;
                
                List<Double> ranges = new ArrayList<Double>();
                
                Arrays.sort(values);
                
                while (true) {
                        count += breakSize;
                        
                        if (count >= size) {
                                count = size - 1;
                                ranges.add(values[count]);
                                break;
                        }
                        
                        ranges.add(values[count - 1]);                        
                }
                
                return ranges;
        }
        
}
