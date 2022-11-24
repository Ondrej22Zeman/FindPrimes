import org.junit.jupiter.api.Test;
import prime_logic.PrimeFinder;

import static org.junit.jupiter.api.Assertions.*;

public class TestPrimeFinder {
    PrimeFinder pf = new PrimeFinder();

    @Test
    public void testNegativeNumber() {
        assertFalse(pf.findPrime(-23));
    }

    @Test
    public void testNumber0() {
        assertFalse(pf.findPrime(0));
    }

    @Test
    public void testNumber1() {
        assertFalse(pf.findPrime(1));
    }

    @Test
    public void testNumber2() {
        assertTrue(pf.findPrime(2));
    }

    @Test
    public void testNumber27() {
        assertFalse(pf.findPrime(27));
    }

    @Test
    public void testNumber29() {
        assertTrue(pf.findPrime(29));
    }

    @Test
    public void testNumber27644437() {
        assertTrue(pf.findPrime(27644437));
    }
}
