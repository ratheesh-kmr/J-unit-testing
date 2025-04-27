package softwaretesting;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

public class LinearRegressionTest {

    @Test
    public void testLinearRegressionPrediction() {
        // Test data (Votes and Prices)
        List<Double> votes = Arrays.asList(84.0, 45.0);
        List<Double> prices = Arrays.asList(249.0, 129.0);
        
        // Create and fit the model
        LinearRegression model = new LinearRegression();
        model.fit(votes, prices);

        // Predict the price for 50 votes
        double predictedPrice = model.predict(50.0);

        // In this case, you would manually calculate the expected value or test for correctness
        double expectedPrice = 164.0;  // Example expected value, adjust based on your calculations
        
        // Allow a margin of error (e.g., 10.0) for the prediction
        assertEquals(expectedPrice, predictedPrice, 10.0);
    }
}
