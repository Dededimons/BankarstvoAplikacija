import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Placanje {
    public static void uplati() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("-- Placanje --");
        System.out.print("Upisi IBAN: ");
        String iban = scanner.nextLine();

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
            System.out.println(" Placanje uspjesno ");
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
}