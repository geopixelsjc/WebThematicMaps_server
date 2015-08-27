/**
 * 
 */
package geopixel.thematic;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

/**
 * @author PauloLuan
 *
 */
public class TestThematicController {
        
        Controller controller;
        
        @Before
        public void initialize() {
                controller = new Controller();
        }
        
        @Test
        public void testCalculateRanges() {
                assertNotNull(controller);
                
                Double[] values = { 1.0, 2., 3., 4., 5., 6., 7., 8., 9., 10. };
                int breaks = 2;
                List<Double> result = controller.calculateRanges(values, breaks);
                assertNotNull(result);
        }
        
        @Test
        public void testGenerateColorsByRange() {
                Double[] values = { 1., 2., 3., 4., 5., 6., 7., 8., 9., 10. };
                int breaks = 4;
                List<Double> calculatedRanges = controller.calculateRanges(values, breaks);
                assertNotNull(calculatedRanges);
                
                HashMap<String, List<Double>> colors = controller.generateColorsByRange(calculatedRanges);
                
                Iterator it = colors.entrySet().iterator();
                
                while (it.hasNext()) {
                        Map.Entry pair = (Map.Entry) it.next();
                        String color = (String) pair.getKey();
                        List<Double> ranges = (List<Double>) pair.getValue();
                }
                
                assertNotNull(colors);
        }
        
        @Test
        public void testIsBetween() {
                assertTrue("2 Should be between 1 and 3", controller.isBetween(1, 3, 2));
                assertTrue("14 Should be between 10 and 20", controller.isBetween(10, 20, 14));
                assertFalse("1 Should not be between 2 and 4", controller.isBetween(2, 4, 1));
                assertFalse("4 Should not be between 10 and 20", controller.isBetween(10, 20, 4));
        }
}
