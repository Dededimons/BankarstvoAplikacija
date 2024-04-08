import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Login {

    private static final String USERNAME = "admin";
    private static final String PASSWORD = encryptPassword("admin");
    private static final String RACUN = "12345";
    private static final double STANJE = 1000.00;
    private static final String VALUTA = "EUR";

    public static String getRacun() {
        return RACUN;
    }

    public static double getStanje() {
        return STANJE;
    }

    public static String getValuta() {
        return VALUTA;
    }



    public static boolean login() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("-- Prijava u aplikaciju --");
        System.out.print("Upišite ime: ");
        String username = scanner.nextLine();
        char[] password = getPasswordFromConsole();

        if (username.equals(USERNAME) && encryptPassword(new String(password)).equals(PASSWORD)) {
            System.out.println("Prijava uspješna");
            return true;
        } else {
            System.out.println("Prijava neuspješna");
            return false;
        }
    }

    private static String encryptPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hashedBytes = md.digest(password.getBytes());

            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            System.err.println("Greška " + e.getMessage());
            return null;
        }
    }

    private static char[] getPasswordFromConsole() {
        System.out.print("Upišite lozinku: ");
        return System.console().readPassword();
    }
}