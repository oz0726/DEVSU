import org.junit.Test;
import com.intuit.karate.Runner;
import com.intuit.karate.Results;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class TestRunner {

    @Test
    public void testAllAPI() {
        Results results = Runner.path("classpath:com/devsu/clientPerson").parallel(1);
        assertEquals(0, results.getFailCount(), results.getErrorMessages());
    }
}