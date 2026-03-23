import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FirstJUnitTest {

    @Test
    public void first() {
        String str = "Hello Fuad";

        assertEquals("Hello Fuad", str);
    }
}