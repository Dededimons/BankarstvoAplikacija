import static org.junit.Assert.*;
import org.junit.*;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.*;

public class PlacanjeTest {

        private void setInput(String input) {
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
    }
    private void resetInput() {
        System.setIn(System.in);
    }

    static final String DATABASE_URL = "jdbc:sqlite:baza.db";
    private Connection connection;

    @Before
    public void setUp() throws Exception {
        connection = DriverManager.getConnection(DATABASE_URL);

        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO korisnik ( username, password, ime, prezime,  iznos, iban) VALUES ( ?, ?, ?, ?, ?,? )")) {
            statement.setString(1,"wowo");
            statement.setString(2, "wowo");
            statement.setString(3, "wow");
            statement.setString(4, "wow");
            statement.setDouble(5, 1000.0);
            statement.setString(6, "HR1");
            statement.executeUpdate();

            statement.setString(1,"ojo");
            statement.setString(2, "ojo");
            statement.setString(3, "joj");
            statement.setString(4, "joj");
            statement.setDouble(5, 700);
            statement.setString(6, "HR2");
            statement.executeUpdate();
        }

        setInput("wowo\nwowo\n");
        assertTrue(Login.login());
    }

    @After
    public void tearDown() throws Exception {
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM korisnik WHERE iban IN (?, ?)")) {
            statement.setString(1, "HR1");
            statement.setString(2, "HR2");
            statement.executeUpdate();
        }

        if (connection != null) {
            connection.close();
        }
    }

    @Test
    public void testUplati_Success() throws SQLException {

        setInput("HR2\n200\n");
        

        Placanje.uplati();


        try (PreparedStatement statement = connection.prepareStatement("SELECT iznos FROM korisnik WHERE iban = ?")) {
            statement.setString(1, "HR1");
            ResultSet resultSet = statement.executeQuery();
            assertTrue(resultSet.next());
            assertEquals(800.0, resultSet.getDouble("iznos"), 0.01);
        }

        try (PreparedStatement statement = connection.prepareStatement("SELECT iznos FROM korisnik WHERE iban = ?")) {
            statement.setString(1, "HR2");
            ResultSet resultSet = statement.executeQuery();
            assertTrue(resultSet.next());
            assertEquals(900, resultSet.getDouble("iznos"), 0.01);
        }
    }

}