import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class tecajnaLista {
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
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        getTecajnaLista();
    }
}