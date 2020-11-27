package domain;

import domain.Kirja;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class EtsijaTest {
    private Etsija etsija;
    
    @Before
    public void setUp() {
        List<Lukuvinkki> vinkkeja = new ArrayList<>();
        vinkkeja.add(new Kirja("Seitsemän veljestä"));
        vinkkeja.add(new Kirja("Sinuhe"));
        vinkkeja.add(new Kirja("Learn C Programming"));
        this.etsija = new Etsija(vinkkeja);
        
    }
    
    @Test
    public void vinkkiLoytyy() {
        List<Lukuvinkki> tulokset = this.etsija.etsiVinkinNimella("ea");

        assertEquals("Learn C Programming", tulokset.get(0).getLabel());
    }
    
}
