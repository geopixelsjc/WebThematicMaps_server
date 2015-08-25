package geopixel.thematic;

import java.awt.Color;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.TreeSet;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public class Controller {
        
        public String calculateQuantil(
                                       Integer classesNumber,
                                       String attributeName,
                                       String attributeValue) throws IOException, SQLException, JSONException {
                                
                List<Double> ranges = generateRanges();
                
                ResultSet geoJsonResultSet = Dao.getFakeQuantil();
                
                while (geoJsonResultSet.next()) {
                        String jsonString = geoJsonResultSet.getString(1);
                        JSONObject obj = new JSONObject(jsonString);
                        JSONArray features = obj.getJSONArray("features");
                        
                        for (int i = 0; i < features.length(); i++) {
                                JSONObject feature = features.getJSONObject(i);
                                JSONObject properties = feature.getJSONObject("properties");
                                Double area = properties.getDouble("area_2013_km2");
                                
                        }
                        
                }
                return null;
        }
        
        public List<Double> generateRanges() throws SQLException, IOException {
                ResultSet areasResultSet = Dao.getAreas();
                TreeSet<Double> areas = new TreeSet<Double>();
                
                while (areasResultSet.next()) {
                        Double area = areasResultSet.getDouble(1);
                        areas.add(area);
                }
                
                Double[] areasArray = areas.toArray(new Double[areas.size()]);
                List<Double> calculatedRanges = calculateRanges(areasArray, 4);
                
                return calculatedRanges;
        }
        
        public void applyColorToGeoJSON(
                                        JSONObject geojson,
                                        String color,
                                        String attributeName,
                                        Double attributeValue) throws JSONException {
                // for no geojson e aplicar quando a area for menor que ...
                //                Double value = geojson.getDouble(attributeName);
                //                
                //                getColorForProperty(range, value);
                //                
                //                if (value < attributeValue) {
                //                        geojson.put("color", color);
                //                }
                
        }
        
        public String getColorForProperty(ArrayList<Double> ranges, Double value) {
                String color = "";
                
                for (int i = 0; i < ranges.size(); i++) {
                        Double range1 = ranges.get(i);
                        Double range2 = ranges.get(i + 1);
                        
                        if (value <= range1 && value >= range2) {
                                color = "RRGGBB";
                        }
                }
                
                return color;
        }
        
        public Color generateRandomColor() {
                Random rand = new Random();
                
                float r = rand.nextFloat();
                float g = rand.nextFloat();
                float b = rand.nextFloat();
                
                Color randomColor = new Color(r, g, b);
                
                return randomColor;
        }
        
        public List<Double> calculateRanges(Double[] values, int breaks) {
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
        
        /**
         * 
         * @param ranges
         *                an array with all ranges that will generate the table
         *                of colors.
         * @return {@link HashMap}<String, List<Double>>
         * 
         *         String - represents the color in hexadecimal List<Double> -
         *         the ranges that this color will be applied
         */
        public HashMap<String, List<Double>> generateColorsByRange(
                                                                   List<Double> ranges) {
                HashMap<String, List<Double>> colors = new HashMap<String, List<Double>>();
                
                for (int i = 0; i < ranges.size(); i += 2) {
                        Color color = generateRandomColor();
                        String hex = String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());
                        List<Double> tempRange;
                        
                        if (i + 2 >= ranges.size()) {
                                tempRange = Arrays.asList(ranges.get(i));
                                colors.put(hex, tempRange);
                                break;
                        }
                        
                        tempRange = ranges.subList(i, i + 2);
                        colors.put(hex, tempRange);
                }
                
                return colors;
        }
}
