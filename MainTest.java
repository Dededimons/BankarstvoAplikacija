import org.junit.Assert;
import org.junit.Test;
import java.io.File;
import java.util.logging.Logger;

public class MainTest {

    @Test
    public void testLogging() {
        Logger logger = Logger.getLogger(Main.class.getName());

        logger.info("Aplikacija je pokrenuta");
        Assert.assertTrue(new File("app.log").exists());
    }
}