import algorithms.Viginer;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ViginerTest {


    @Test
    public void testEncryption(){
        Viginer viginer = new Viginer("YES");
        String cipher = viginer.encrypt("HELLO");
        assertEquals("E(HELLO) = FIDJS", "FIDJS", cipher);
    }

    @Test
    public void testDecryption(){
        Viginer viginer = new Viginer("YES");
        String cipher = viginer.decrypt("FIDJS");
        assertEquals("E(FIDJS) = HELLO", "HELLO", cipher);
    }

}
