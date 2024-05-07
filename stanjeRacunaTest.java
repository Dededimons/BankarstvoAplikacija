import org.junit.*;
import static org.junit.Assert.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class stanjeRacunaTest {

    @Test
    public void testCheckStanje() {

        String ime = "teststanje";
        String prezime = "teststanje";
        String iban = "HR123";
        double iznos = 1000.0;
        Login.setUsername("teststanje");
        Login.setIme(ime);
        Login.setPrezime(prezime);
        Login.setIBAN(iban);
        Login.setIznos(iznos);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        stanjeRacuna.checkStanje();

        System.setOut(originalOut);

        String printedOutput = outputStream.toString();

        String expectedOutput = "-- Stanje racuna --\n" +
                                "Ime: " + ime + "\n" +
                                "Prezime: " + prezime + "\n" +
                                "IBAN: " + iban + "\n" +
                                "Iznos: " + iznos + "\n";

    expectedOutput = expectedOutput.replace("\r\n", "\n").replace("\r", "\n");
    printedOutput = printedOutput.replace("\r\n", "\n").replace("\r", "\n");
        assertEquals(expectedOutput, printedOutput);

    }
    
}