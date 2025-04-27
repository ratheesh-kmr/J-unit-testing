
package softwaretesting;

import java.util.List;

public class LinearRegression {

    private double intercept;
    private double slope;

    // Fit the model to the data
    public void fit(List<Double> xValues, List<Double> yValues) {
        if (xValues.size() != yValues.size()) {
            throw new IllegalArgumentException("xValues and yValues must have the same length");
        }

        int n = xValues.size();
        double sumX = 0;
        double sumY = 0;
        double sumXY = 0;
        double sumX2 = 0;

        for (int i = 0; i < n; i++) {
            sumX += xValues.get(i);
            sumY += yValues.get(i);
            sumXY += xValues.get(i) * yValues.get(i);
            sumX2 += xValues.get(i) * xValues.get(i);
        }

        // Calculate the slope and intercept
        this.slope = (n * sumXY - sumX * sumY) / (n * sumX2 - sumX * sumX);
        this.intercept = (sumY - slope * sumX) / n;
    }

    // Predict y value for a given x value
    public double predict(double x) {
        return intercept + slope * x;
    }
}
