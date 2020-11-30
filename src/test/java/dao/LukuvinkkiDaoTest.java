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
    public void setUp() {

        l = new Kirja("Moby Dick");
        v = new Kirja("Huckeleberry Fin");

        dao = new LukuvinkkiDao();

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

}