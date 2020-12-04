package domain;

import domain.Lukuvinkki;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class LukuvinkkiTest {
    
    private LukuvinkkiImpl lukuvinkki;
    
    @Before
    public void setUp() {
        lukuvinkki = new LukuvinkkiImpl();
    }

    @Test
    public void testSetAndGetLabel() {
        lukuvinkki.setLabel("testLabel2");
        assertEquals(lukuvinkki.getLabel(), "testLabel2");
    }
    
    @Test
    public void testSetAndGetType() {
        lukuvinkki.setType("testType");
        assertEquals(lukuvinkki.getType(), "testType");
    }
    
    @Test
    public void testToString() {
        lukuvinkki.setLabel("testLabel3");
        assertEquals(lukuvinkki.toString(), "testLabel3 URL: NIL");
    }
    
    @Test
    public void testAddTag() {
        lukuvinkki.addTagi("test1");
        lukuvinkki.addTagi("test2");
        assertEquals(lukuvinkki.getTagit().size(), 2);
        assertEquals(lukuvinkki.getTagit().get(1), "test2");
        
        
    }

    public class LukuvinkkiImpl extends Lukuvinkki {
        
        public LukuvinkkiImpl() {
            super("testLabel");
        }
    }
    
}
