import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Login {

    private static final String DATABASE_URL = "jdbc:sqlite:baza.db";

    private static String username;
    private static String ime;
    private static String prezime;
    private static String iban;
    private static double iznos;
    private static String valuta;

    public static String getUsername() {
        return username;
    }

    public static String getIme() {
        return ime;
    }
    public static String getPrezime() {
        return prezime;
    }

    public static String getIBAN() {
        return iban;
    }

    public static double getIznos() {
        return iznos;
    }

    public static String getValuta() {
        return valuta;
    }

    public static boolean login() {
        Scanner scanner = new Scanner(System.in);
    
        System.out.println("-- Prijava u aplikaciju --");
        System.out.print("Upišite username: ");
        String username = scanner.nextLine();
        System.out.print("Upišite lozinku: ");
        String password = scanner.nextLine();
    
        try (Connection connection = DriverManager.getConnection(DATABASE_URL)) {
            String query = "SELECT username, ime, prezime, iban, iznos FROM korisnik WHERE username = ? AND password = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, username);
                statement.setString(2, password);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        username = resultSet.getString("username");
                        ime = resultSet.getString("ime");
                        prezime = resultSet.getString("prezime");
                        iban = resultSet.getString("iban");
                        iznos = resultSet.getDouble("iznos");

                        
                        System.out.println("Prijava uspješna");
                        return true;
                    } else {
                        System.out.println("Prijava neuspješna");
                        return false;
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Greška pri spajanju na bazu podataka: " + e.getMessage());
            return false;
        }
    }

}