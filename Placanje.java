import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Placanje {
    public static void uplati() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("-- Placanje --");
        System.out.print("Upisi IBAN: ");
        
        String iban;
        do {
            System.out.print("Upiši IBAN: ");
            iban = scanner.nextLine();
        } while (iban.length() != 32);
        

        System.out.print("Upisi iznos placanja: ");
        double value = scanner.nextDouble();

        System.out.print("Datum (dd/mm/yyyy): ");
        String dateString = scanner.next();
        Date datum;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            datum = dateFormat.parse(dateString);
        } catch (Exception e) {
            System.out.println("Krivi format datuma");
            return;
        }

        if (uzmiPare(value)) {
            System.out.println("Placanje uspjesno");
            savePaymentToFile(iban, value, dateString);
        } else {
            System.out.println("Greska");
        }
    }

    private static boolean uzmiPare(double iznos) {
        double stanje = Login.getStanje();
        if (stanje >= iznos) {
            double novoStanje = stanje - iznos;
            Login.setStanje(novoStanje);
            return true;
        } else {
            return false;
        }
    }

    private static void savePaymentToFile(String iban, double value, String dateString) {
        try {
            FileWriter writer = new FileWriter("payments.txt", true);
            writer.write("IBAN: " + iban + ", Iznos: " + value + ", Datum: " + dateString + "\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("Greška: " + e.getMessage());
        }
    }
}