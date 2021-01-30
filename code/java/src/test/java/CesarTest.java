import algorithms.Cesar;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CesarTest {

    @Test
    public void testEncryption() {
        Cesar cesar = new Cesar(2);
        assertEquals("Cesar encryption error , 'ABCD' ==> 'CDEF'",
                "CDEF", cesar.encrypt("ABCD"));
    }
}
