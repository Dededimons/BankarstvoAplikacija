import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Main {

    private static final Logger logger = Logger.getLogger(Main.class.getName());
    public static void main(String[] args) {

        try {
            FileHandler fileHandler = new FileHandler("app.log");
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Greška pri postavljanju file handlera", e);
        }

        logger.info("Aplikacija je pokrenuta");

        if (Login.login()) {
            Menu.displayMenu();
        } else {
            System.out.println("Izlazak");
        }
    }
}