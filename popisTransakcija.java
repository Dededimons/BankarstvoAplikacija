import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class popisTransakcija {
    public static void displayPayments() {
        System.out.println("-- Popis transakcija --");
        try {
            BufferedReader reader = new BufferedReader(new FileReader("payments.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Greška: " + e.getMessage());
        }
    }
}