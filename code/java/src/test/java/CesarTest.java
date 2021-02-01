import algorithms.Cesar;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CesarTest {

    final Cesar cesar = new Cesar(2);

    @Test
    public void testEncryption() {

        // simple encryption
        assertEquals("Cesar encryption error , 'ABCD' ==> 'CDEF'",
                "CDEF", cesar.encrypt("ABCD"));

        // it should ignore special chars / numbers
        assertEquals("It should ignore special chars", "123,;", cesar.encrypt("123,;"));

        // it should ignore special chars / number but encrypt the alphabets
        assertEquals("it should ignore special chars / number but encrypt the alphabets",
                "123C,d;Ee", cesar.encrypt("123A,b;Cc"));
    }

    @Test
    public void testDecryption() {
        assertEquals("Cesar Encryption should return ABCD", "ABCD", cesar.decrypt("CDEF"));
        assertEquals("Cesar Encryption should ignore special-char/numbers", ",;/1",
                cesar.decrypt(",;/1"));
        assertEquals("Cesar Encryption should ignore special-char/numbers - test 2", ",;/1Abb\\",
                cesar.decrypt(",;/1Cdd\\"));
    }

}
