import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class CurrencyConverter {

    private static final String API_KEY = "6602b4537dd8673ec4aabe16";
    private static final String API_BASE_URL = "https://v6.exchangerate-api.com/v6/";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Enter the base currency (e.g., USD): ");
            String baseCurrency = scanner.next().toUpperCase();

            System.out.print("Enter the target currency (e.g., EUR): ");
            String targetCurrency = scanner.next().toUpperCase();

            System.out.print("Enter the amount to convert: ");
            double amount = scanner.nextDouble();

            double exchangeRate = getExchangeRate(baseCurrency, targetCurrency);

            if (exchangeRate > 0) {
                double convertedAmount = amount * exchangeRate;
                System.out.printf("\n%.2f %s = %.2f %s\n", amount, baseCurrency, convertedAmount, targetCurrency);
            } else {
                System.out.println("Could not fetch the exchange rate. Please check your currencies and API key.");
            }

        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    private static double getExchangeRate(String base, String target) throws Exception {
        String urlString = API_BASE_URL + API_KEY + "/latest/" + base;
        URL url = new URL(urlString);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(response.toString());
            JsonNode ratesNode = rootNode.get("conversion_rates");

            if (ratesNode != null && ratesNode.has(target)) {
                return ratesNode.get(target).asDouble();
            }
        }
        return -1.0;
    }
}