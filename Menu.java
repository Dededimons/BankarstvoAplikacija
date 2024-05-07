import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Menu {

    private static final Logger logger = Logger.getLogger(Menu.class.getName());

    public static void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean loggedIn = true;

        while (loggedIn) {
            System.out.println("-- Menu --");
            System.out.println("1. Logout");
            System.out.println("2. Stanje racuna");
            System.out.println("3. Placanje");
            System.out.println("4. Popis transakcija");
            System.out.println("5. Tecajna lista");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    logger.log(Level.INFO, "Korisnik odabrao opciju: Logout");
                    loggedIn = logoutConfirm();
                    break;
                case 2:
                    logger.log(Level.INFO, "Korisnik odabrao opciju: Stanje racuna");
                    stanjeRacuna.checkStanje();
                    break;
                case 3:
                    logger.log(Level.INFO, "Korisnik odabrao opciju: Placanje");
                    Placanje.uplati();
                    break;
                case 4:
                    logger.log(Level.INFO, "Korisnik odabrao opciju: Popis transakcija");
                    popisTransakcija.displayPayments();
                    break;
                case 5:
                    logger.log(Level.INFO, "Korisnik odabrao opciju: Tecajna lista");
                    tecajnaLista.getTecajnaLista();
                    break;
                default:
                    logger.log(Level.WARNING, "Korisnik odabrao nepostojeću opciju: " + choice);
                    System.out.println("Pogreška");
            }
        }
    }

    private static boolean logoutConfirm() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Jeste li sigurni da želite odjaviti? (Y/N): ");
        String confirmation = scanner.next();
        if (confirmation.equalsIgnoreCase("Y")) {
            return false;
        } else if (confirmation.equalsIgnoreCase("N")) {
            return true;
        } else {
            System.out.println("Molimo unesite Y ili N ");
            return true;
        }
    }
}