package dao;

import domain.Kirja;
import domain.Lukuvinkki;
import dao.LukuvinkkiDao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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

        l = new Kirja("Moby Dick");
        v = new Kirja("Huckleberry Finn");
        vinkkeja = new ArrayList<Lukuvinkki>();
        vinkkeja.add(l);
        vinkkeja.add(v);
        dao = new LukuvinkkiDao();
        dao.useTestFile();

    }

    @Test
    public void tallennusOikein() throws IOException, FileNotFoundException {
        
        dao.saveToFile(l);

        List<Lukuvinkki> haetutVin = dao.readFromFile();

        int koko = haetutVin.size() - 1;

        assertEquals(l.getLabel(), haetutVin.get(koko).getLabel());
    }

    @Test
    public void tallennusOikeinKahdesti() throws IOException, FileNotFoundException {
        
        dao.saveToFile(l);
        dao.saveToFile(v);
        List<Lukuvinkki> haetutVin = dao.readFromFile();

        int koko = haetutVin.size() - 1;

        assertEquals(v.getLabel(), haetutVin.get(koko).getLabel());
    }

    @Test
    public void listanTallennusToimii() throws IOException, FileNotFoundException {

        dao.saveListToFile(vinkkeja);

        assertEquals(l.getLabel(), dao.readFromFile().get(0).getLabel());

    }

}