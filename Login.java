import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Login {

    static final String DATABASE_URL = "jdbc:sqlite:baza.db";
    private static String username;
    private static String ime;
    private static String prezime;
    private static String iban;
    private static double iznos;

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

    public static boolean login() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("-- Prijava u aplikaciju --");
        System.out.print("Upišite username: ");
        String usernameInput = scanner.nextLine();
        System.out.print("Upišite lozinku: ");
        String passwordInput = scanner.nextLine();

        try (Connection connection = establishConnection()) {
            if (connection != null) {
                return executeQuery(connection, usernameInput, passwordInput);
            } else {
                return false;
            }
        } catch (SQLException e) {
            System.err.println("Greška pri spajanju na bazu podataka: " + e.getMessage());
            return false;
        }
    }

     static Connection establishConnection() throws SQLException {
        return DriverManager.getConnection(DATABASE_URL);
    }

    private static boolean executeQuery(Connection connection, String usernameInput, String passwordInput) throws SQLException {
        String query = "SELECT username, ime, prezime, iban, iznos FROM korisnik WHERE username = ? AND password = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, usernameInput);
            statement.setString(2, passwordInput);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    setUsername(resultSet.getString("username"));
                    setIme(resultSet.getString("ime"));
                    setPrezime(resultSet.getString("prezime"));
                    setIBAN(resultSet.getString("iban"));
                    setIznos(resultSet.getDouble("iznos"));
                    System.out.println("Prijava uspješna");
                    return true;
                } else {
                    System.out.println("Prijava neuspješna");
                    return false;
                }
            }
        }
    }

    static void setUsername(String user) {
        username = user;
    }

     static void setIme(String name) {
        ime = name;
    }

     static void setPrezime(String surname) {
        prezime = surname;
    }

     static void setIBAN(String accountNumber) {
        iban = accountNumber;
    }

     static void setIznos(double amount) {
        iznos = amount;
    }

    public static void logout() {
        username = null;
    }
}