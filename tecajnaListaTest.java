import org.junit.*;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;


public class tecajnaListaTest {

    @Test
    public void testGetTecajnaLista_ConnectionSuccess() {
        try {

            tecajnaLista.getTecajnaLista();

            assertTrue(true);
        } catch (Exception e) {
            fail("Greska: " + e.getMessage());
        }
    }
}