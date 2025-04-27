package softwaretesting;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

public class App {
    public static void main(String[] args) {
        String csvFile = "C:/Users/rathe/Documents/Ratheesh got bored/SEM 6/J unit testing/junit/src/Dataset/zomato.csv"; 
        List<Double> votes = new ArrayList<>();
        List<Double> prices = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new FileReader(csvFile))) {
            String[] line;
            boolean isFirstLine = true;
            while ((line = reader.readNext()) != null) {
                if (isFirstLine) {
                    isFirstLine = false; // Skip header
                    continue;
                }

                try {
                    // Handle rows properly, assuming columns might shift (Place Name, City)
                    int len = line.length;
                    if (len >= 12) {
                        double vote = Double.parseDouble(line[len - 2].trim());
                        double price = Double.parseDouble(line[len - 1].trim());
                        votes.add(vote);
                        prices.add(price);
                    } else {
                        System.out.println("Skipping row due to insufficient columns: " + String.join(",", line));
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Skipping row due to number format error: " + String.join(",", line));
                }
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }

        if (votes.isEmpty() || prices.isEmpty()) {
            System.out.println("No valid data available to train the model.");
            return;
        }

        // Create and train the model
        LinearRegression model = new LinearRegression();
        model.fit(votes, prices);

        // Example prediction
        double predictedPrice = model.predict(50.0);
        System.out.println("Predicted price for 50 votes: " + predictedPrice);
    }
}
