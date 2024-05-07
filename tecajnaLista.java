import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class tecajnaLista {

    private static final Logger logger = Logger.getLogger(tecajnaLista.class.getName());
    

    public static void getTecajnaLista() {
        try {
            URL url = new URL("https://api.hnb.hr/tecajn-eur/v3");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            JSONArray currencies = new JSONArray(response.toString());

            logger.log(Level.INFO, "Prikazana tecajna lista");

            System.out.println("Tecajna lista:");
            for (int i = 0; i < currencies.length(); i++) {
                JSONObject currency = currencies.getJSONObject(i);
                String code = currency.getString("valuta");
                String country = currency.getString("drzava");
                String buyRate = currency.getString("kupovni_tecaj");
                String sellRate = currency.getString("prodajni_tecaj");

                System.out.println("Valuta: " + code);
                System.out.println("Drzava: " + country);
                System.out.println("Kupovni tecaj: " + buyRate);
                System.out.println("Prodajni tecaj: " + sellRate);
                System.out.println();
            }

            connection.disconnect();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error dohvacanja liste " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        setupLogger();
        getTecajnaLista();
    }

    private static void setupLogger() {
        try {
            FileHandler fileHandler = new FileHandler("app.log");
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error sa loggerom " + e.getMessage());
        }
    }
}