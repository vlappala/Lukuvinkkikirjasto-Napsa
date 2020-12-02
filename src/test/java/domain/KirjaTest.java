package domain;

import domain.Kirja;
import static org.junit.Assert.*;
import org.junit.Test;

public class KirjaTest {

    @Test
    public void tulostusOikein() {
        Kirja test = new Kirja("Moby Dick");

        assertEquals("Moby Dick URL: NIL", test.toString());
    }

}
