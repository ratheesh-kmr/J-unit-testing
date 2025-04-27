package softwaretesting;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

public class LinearRegressionTest {

    @Test
    void testFitAndPredictSimpleCase() {
        LinearRegression model = new LinearRegression();
        model.fit(Arrays.asList(1.0, 2.0, 3.0), Arrays.asList(3.0, 5.0, 7.0));

        double predicted = model.predict(4.0);
        assertEquals(9.0, predicted, 0.0001);
    }

    @Test
    void testInterceptAndSlopeCalculation() {
        LinearRegression model = new LinearRegression();
        model.fit(Arrays.asList(1.0, 2.0, 3.0), Arrays.asList(2.0, 4.0, 6.0));

        assertEquals(2.0, model.predict(1.0), 0.0001);
        assertEquals(4.0, model.predict(2.0), 0.0001);
    }

    @Test
    void testMismatchedInputSizes() {
        LinearRegression model = new LinearRegression();

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            model.fit(Arrays.asList(1.0, 2.0), Arrays.asList(3.0));
        });

        String expectedMessage = "xValues and yValues must have the same length";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testMeanCalculation() {
        LinearRegression model = new LinearRegression();
        double mean = model.mean(Arrays.asList(2.0, 4.0, 6.0));
        assertEquals(4.0, mean, 0.0001);
    }

    @Test
    void testStandardDeviationCalculation() {
        LinearRegression model = new LinearRegression();
        double stddev = model.standardDeviation(Arrays.asList(2.0, 4.0, 6.0));
        assertEquals(1.63299, stddev, 0.0001);
    }
}
