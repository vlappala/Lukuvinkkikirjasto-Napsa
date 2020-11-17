package classes;

import static org.junit.Assert.*;
import org.junit.Test;

public class KirjaTest {

    @Test
    public void tulostusOikein() {
        Kirja test = new Kirja("Moby Dick");

        assertEquals("Moby Dick: Author: NIL, ISBN: NIL", test.toString());
        }

}
