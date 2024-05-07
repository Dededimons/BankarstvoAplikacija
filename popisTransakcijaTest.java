import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class popisTransakcijaTest {

    @Test
    public void testDisplayPaymentsFromFile() throws IOException {

        File testFile = new File("test_placanja.txt");
        FileWriter writer = new FileWriter(testFile);
        writer.write("Datum: 2024/05/01, Šalje: senderIBAN, Prima: receiverIBAN, Iznos: 100.0\n");
        writer.close();


        popisTransakcija.displayPaymentsFromFile();
        testFile.delete();
    }
}