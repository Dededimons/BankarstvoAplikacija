import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class popisTransakcija {

    private static final String DATABASE_URL = "jdbc:sqlite:baza.db";

    public static void displayPayments() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Odaberite način prikaza transakcija:");
        System.out.println("1. Prikazi transakcije iz baze podataka");
        System.out.println("2. Prikazi transakcije iz placanja.txt datoteke");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                displayPaymentsFromDatabase();
                break;
            case 2:
                displayPaymentsFromFile();
                break;
            default:
                System.out.println("Neispravan izbor.");
        }
    }

    private static void displayPaymentsFromDatabase() {
        System.out.println("-- Popis transakcija iz baze podataka --");
        try (Connection connection = DriverManager.getConnection(DATABASE_URL)) {
            String query = "SELECT * FROM placanja WHERE placa = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, Login.getIBAN());
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        String timestamp = resultSet.getString("datum");
                        String sender = resultSet.getString("placa");
                        String receiver = resultSet.getString("prima");
                        double amount = resultSet.getDouble("iznos");
                        System.out.println("Datum: " + timestamp + ", Šalje: " + sender + ", Prima: " + receiver + ", Iznos: " + amount);
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Greška pri dohvaćanju transakcija iz baze podataka: " + e.getMessage());
        }
    }

    private static void displayPaymentsFromFile() {
        System.out.println("-- Popis transakcija iz placanja.txt --");
        String currentUserIBAN = Login.getIBAN();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("placanja.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                // Split the line into parts
                String[] parts = line.split(", ");
                // Extract sender's IBAN from the line
                String senderIBAN = parts[1].substring(7); // Assuming the sender is always after "Šalje: "
                // Check if the sender's IBAN matches the current user's IBAN
                if (senderIBAN.equals(currentUserIBAN)) {
                    System.out.println(line);
                }
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Greška: " + e.getMessage());
        }
    }
}