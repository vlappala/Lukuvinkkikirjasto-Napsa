package dao;

import domain.Kirja;
import domain.Lukuvinkki;
import dao.LukuvinkkiDao;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;

public class LukuvinkkiDaoTest {

    List<Lukuvinkki> vinkkeja;
    LukuvinkkiDao dao;
    Lukuvinkki l;
    Lukuvinkki v;

    @Before
    public void setUp() throws IOException, FileNotFoundException {

        l = new Lukuvinkki("Moby Dick");
        v = new Lukuvinkki("Huckleberry Finn");
        vinkkeja = new ArrayList<Lukuvinkki>();
        vinkkeja.add(l);
        vinkkeja.add(v);
        dao = new LukuvinkkiDao();
        dao.useTestFile();

    }
    
    // tyhjennet‰‰n file jokaisen testin j‰lkeen
    @After
    public void tearDown() throws FileNotFoundException, IOException {
        FileWriter pw = new FileWriter("./Test.txt");
        pw.close();
    }

    @Test
    public void tallennusOikein() throws IOException, FileNotFoundException {
        
        dao.saveToFile(l);

        List<Lukuvinkki> haetutVin = dao.readFromFile();

        int koko = haetutVin.size() - 1;

        assertEquals(l.getLabel(), haetutVin.get(koko).getLabel());
        assertEquals(1, haetutVin.size());
    }

    @Test
    public void tallennusOikeinKahdesti() throws IOException, FileNotFoundException {
        
        dao.saveToFile(l);
        dao.saveToFile(v);
        
        List<Lukuvinkki> haetutVin = dao.readFromFile();

        int koko = haetutVin.size() - 1;

        assertEquals(v.getLabel(), haetutVin.get(koko).getLabel());
        assertEquals(2, haetutVin.size());
    }

    @Test
    public void listanTallennusToimii() throws IOException, FileNotFoundException {

        dao.saveListToFile(vinkkeja);

        assertEquals(l.getLabel(), dao.readFromFile().get(0).getLabel());
    }
    
    @Test
    public void poistoToimiiKunPoistetaanAinoaVinkki() throws IOException, FileNotFoundException {
        
        dao.saveToFile(l);
        
        dao.deleteFromFile(l);
        
        List<Lukuvinkki> haetutVin = dao.readFromFile();
        
        assertEquals(0, haetutVin.size());
        
    }
    
    @Test
    public void poistoToimiiKunJaljelleJaaYksiVinkki() throws IOException, FileNotFoundException {
        
        dao.saveToFile(l);
        dao.saveToFile(v);
        
        dao.deleteFromFile(v);
        
        List<Lukuvinkki> haetutVin = dao.readFromFile();
        
        assertEquals(1, haetutVin.size());
        
    }
    
}