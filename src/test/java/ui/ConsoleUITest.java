package ui;

import dao.LukuvinkkiDao;
import domain.Kirja;
import domain.Lukuvinkki;
import io.ConsoleIO;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class ConsoleUITest {

    private final String FILE = "Testi";
    private final Kirja vinkki = new Kirja("Raamattu");
    private final ArrayList<Lukuvinkki> vinkit = new ArrayList<>();

    ConsoleIO console;
    LukuvinkkiDao dao;
    ConsoleUI ui;

    @Before
    public void setUp() {
        console = mock(ConsoleIO.class);
        dao = mock(LukuvinkkiDao.class);
        ui = new ConsoleUI(console, dao);
        vinkit.add(new Kirja("Koraani"));
        vinkit.add(new Kirja("Pieni punainen kirja"));
    }

    @Test(timeout = 1000)
    public void uiKysyyToiminnon() {
        try {
            when(dao.readFromFile(FILE)).thenReturn(vinkit);
        } catch (Exception e) {
        }
        ui.run();
        verify(console).readInput("\nValitse vinkki numerolla tai kirjoita teksti hakua varten:");
    }

    @Test(timeout = 1000)
    public void uiTallentaaVinkin() {
        try {
            when(dao.readFromFile(FILE)).thenReturn(vinkit);
        } catch (Exception e) {
        }
        when(console.readInput("\nValitse vinkki numerolla tai kirjoita teksti hakua varten:")).thenReturn("0");
        when(console.readInput("\nAnna lukuvinkin otsikko: ")).thenReturn("Raamattu");
        ui.run();
        try {
            verify(dao).saveToFile(FILE, vinkki);
        } catch (Exception e) {
        }
    }

    @Test(timeout = 1000)
    public void uiTulostaaVinkin() {
        try {
            when(dao.readFromFile(FILE)).thenReturn(vinkit);
        } catch (Exception e) {
        }
        when(console.readInput("\nValitse vinkki numerolla tai kirjoita teksti hakua varten:")).thenReturn("0");
        when(console.readInput("\nAnna lukuvinkin otsikko: ")).thenReturn("Raamattu");
        ui.run();
        verify(console).printOutput("Luotiin lukuvinkki: Raamattu");
    }
}
