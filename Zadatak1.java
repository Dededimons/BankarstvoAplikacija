import java.util.Scanner;

public class Zadatak1 {

    private static final String USERNAME = "admin";
    private static final String PASSWORD = "admin";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("-- Prijava u aplikaciju --");
        System.out.print("Upišite ime: ");
        String username = scanner.nextLine();
        char[] password = getPasswordFromConsole();

        if (username.equals(USERNAME) && new String(password).equals(PASSWORD)) {
            System.out.println("Prijava uspješna");
        } else {
            System.out.println("Prijava neuspješna");
        }
    }

    private static char[] getPasswordFromConsole() {
        System.out.print("Upišite lozinku: ");
        return System.console().readPassword();
    }
}