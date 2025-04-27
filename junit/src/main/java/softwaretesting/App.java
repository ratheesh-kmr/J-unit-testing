package softwaretesting;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        // Path to the Zomato dataset CSV file
        String csvFile = "src/Dataset/zomato.csv";

        // Lists to hold the Votes and Prices data
        List<Double> votes = new ArrayList<>();
        List<Double> prices = new ArrayList<>();

        // Read the CSV file
        try (CSVReader reader = new CSVReader(new FileReader(csvFile))) {
            String[] nextLine;
            reader.readNext(); // Skip header row if there is one

            while ((nextLine = reader.readNext()) != null) {
                try {
                    // Assuming the 'Votes' is at index 9 and 'Prices' is at index 11 (adjust accordingly)
                    double vote = Double.parseDouble(nextLine[9]); // Column for votes
                    double price = Double.parseDouble(nextLine[11]); // Column for prices
                    
                    // Add the values to the lists
                    votes.add(vote);
                    prices.add(price);
                } catch (NumberFormatException e) {
                    // Handle invalid numbers or missing data
                    System.out.println("Skipping row due to invalid data: " + String.join(",", nextLine));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvValidationException e) {
            // Handle the CSV validation exception
            System.out.println("Error reading the CSV file: " + e.getMessage());
        }

        // Create and fit the model
        LinearRegression model = new LinearRegression();
        model.fit(votes, prices);

        // Make a prediction for a new value (e.g., 50 votes)
        double predictedPrice = model.predict(50.0);

        System.out.println("Predicted price for 50 votes: " + predictedPrice);
    }
}
