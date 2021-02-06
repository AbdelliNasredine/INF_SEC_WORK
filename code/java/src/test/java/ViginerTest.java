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

}
