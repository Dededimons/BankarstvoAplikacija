import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Placanje {
    private static final String DATABASE_URL = "jdbc:sqlite:baza.db";

    public static void uplati() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("-- Placanje --");
        System.out.print("Upiši IBAN primatelja: ");
        String receiverIban = scanner.nextLine();

        System.out.print("Upiši iznos placanja: ");
        double amount = scanner.nextDouble();

        // Get the sender's IBAN from the database or any other method you use for authentication
        String senderIban = Login.getIBAN(); // Replace with the actual sender's IBAN

        // Update account balances in the database
        if (updateAccountBalances(senderIban, receiverIban, amount)) {
            System.out.println("Placanje uspjesno");
        } else {
            System.out.println("Greska");
        }
    }

    private static boolean updateAccountBalances(String senderIban, String receiverIban, double amount) {
        try (Connection connection = DriverManager.getConnection(DATABASE_URL)) {
            // Check if sender has sufficient balance
            double senderBalance = getAccountBalance(connection, senderIban);
            if (senderBalance < amount) {
                System.out.println("Nedovoljno sredstava na računu.");
                return false;
            }

            // Update sender's balance
            double newSenderBalance = senderBalance - amount;
            updateAccountBalance(connection, senderIban, newSenderBalance);

            // Update receiver's balance
            double receiverBalance = getAccountBalance(connection, receiverIban);
            double newReceiverBalance = receiverBalance + amount;
            updateAccountBalance(connection, receiverIban, newReceiverBalance);

            return true;
        } catch (SQLException e) {
            System.err.println("Greška pri ažuriranju računa: " + e.getMessage());
            return false;
        }
    }

    private static double getAccountBalance(Connection connection, String iban) throws SQLException {
        String query = "SELECT iznos FROM korisnik WHERE iban = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, iban);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getDouble("iznos");
                } else {
                    throw new SQLException("Account not found for IBAN: " + iban);
                }
            }
        }
    }

    private static void updateAccountBalance(Connection connection, String iban, double newBalance) throws SQLException {
        String query = "UPDATE korisnik SET iznos = ? WHERE iban = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setDouble(1, newBalance);
            statement.setString(2, iban);
            statement.executeUpdate();
        }
    }
}