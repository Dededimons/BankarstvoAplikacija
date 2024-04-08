import java.util.Scanner;

public class Menu {
    public static void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean loggedIn = true;

        while (loggedIn) {
            System.out.println("-- Menu --");
            System.out.println("1. Logout");
            System.out.println("2. Stanje racuna");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    loggedIn = logoutConfirm();
                    break;
                case 2:
                    stanjeRacuna.checkBalance();
                    break;
                default:
                    System.out.println("Pogreška");
            }
        }
    }

    private static boolean logoutConfirm() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Are you sure you want to logout? (Y/N): ");
        String confirmation = scanner.next();
        if (confirmation.equalsIgnoreCase("Y")) {
            return false; 
        } else if (confirmation.equalsIgnoreCase("N")) {
            return true; 
        } else {
            System.out.println("Upišiti Y ili N ");
            return true; 
        }
    }
}