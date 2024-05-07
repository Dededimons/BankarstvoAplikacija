import static org.junit.Assert.*;
import org.junit.*;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;

public class LoginTest {


    private void setInput(String input) {
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
    }
    private void resetInput() {
        System.setIn(System.in);
    }

    @Test
    public void testLoginSuccess() throws SQLException {

        setInput("test\ntest\n");
        assertTrue(Login.login());
        assertEquals("test", Login.getUsername());
        Login.logout();
        resetInput();

    }

    @Test
    public void testEstablishConnection() throws SQLException {
        Connection connection = Login.establishConnection();
        assertNotNull(connection);
    }


    @Test
    public void testLoginFailure() throws SQLException {
        setInput("krivo\nkrivo\n");
        assertFalse(Login.login());
        assertNull(Login.getUsername());
        Login.logout();
        resetInput();
    }
}