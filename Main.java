import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Main {

    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {

        try {
            FileHandler fileHandler = new FileHandler("app.log", true);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Greška pri postavljanju file handlera", e);
        }

        logger.info("Aplikacija je pokrenuta");

        Thread.setDefaultUncaughtExceptionHandler((thread, throwable) -> {
            logger.log(Level.SEVERE, "Nepokrivena iznimka uhvaćena", throwable);
        });

        try {
            if (Login.login()) {
                Menu.displayMenu();
            } else {
                System.out.println("Izlazak");
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Nepokrivena iznimka uhvaćena u glavnoj metodi", e);
        } finally {
            logger.info("Aplikacija je zatvorena");
        }
    }
}



//            Korisnici u bazi                       //

/* id	username	password	ime	   prezime	          iznos 	iban
1	   test	         test	    Filip  Filipovic	      1490.0	HR123456789012345
2	   test2	     test2	    Ivo	   Ivic	              510.0	    HR98765432101234
18	   testuser	     testuser	Ivo	   Ivic	              100.0	    HR123456789012345678 
3	  teststanje	teststanje	teststanje	teststanje	1000.0	    HR123*/