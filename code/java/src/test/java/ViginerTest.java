import algorithms.Viginer;
import org.junit.Test;

public class ViginerTest {


    @Test
    public void testEncryption(){
        Viginer viginer = new Viginer("abcd");
        viginer.encrypt("hello");
    }

}
