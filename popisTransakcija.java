import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class popisTransakcija {
    public static void displayPayments(String sortBy) {
        System.out.println("-- Popis transakcija --");
        try {
            BufferedReader reader = new BufferedReader(new FileReader("payments.txt"));
            List<String> payments = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                payments.add(line);
            }
            reader.close();

            switch (sortBy) {
                case "Datum":
                    Collections.sort(payments, Comparator.comparing(popisTransakcija::extractDate));
                    break;
                case "Iznos":
                    Collections.sort(payments, Comparator.comparing(popisTransakcija::extractValue));
                    break;
                default:
                    System.out.println("Greska");
                    Collections.sort(payments, Comparator.comparing(popisTransakcija::extractDate));
            }


            for (String payment : payments) {
                System.out.println(payment);
            }
        } catch (IOException e) {
            System.out.println("Greška: " + e.getMessage());
        }
    }

    private static String extractDate(String payment) {
        return payment.split(",")[0];
    }

    private static Double extractValue(String payment) {
        return Double.parseDouble(payment.split(",")[1]);
    }
}