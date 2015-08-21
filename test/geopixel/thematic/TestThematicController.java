/**
 * 
 */
package geopixel.thematic;

import static org.junit.Assert.assertNotNull;

import java.util.List;

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
                
                double[] values = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
                int breaks = 2;
                
                List<Double> result = controller.calculateRanges(values, breaks);
                assertNotNull(result);                
        }
}
