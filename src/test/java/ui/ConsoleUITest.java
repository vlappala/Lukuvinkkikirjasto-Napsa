package ui;

import dao.LukuvinkkiDao;
import domain.Kirja;
import io.ConsoleIO;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class ConsoleUITest {

    private final String FILE = "Vinkit";
    private final Kirja vinkki = new Kirja("Raamattu");

    ConsoleIO console;
    LukuvinkkiDao dao;
    ConsoleUI ui;

    @Before
    public void setUp() {
        console = mock(ConsoleIO.class);
        dao = mock(LukuvinkkiDao.class);
        ui = new ConsoleUI(console, dao);
    }

    @Test
    public void uiKysyyVinkin() {
        ui.run();
        verify(console).readInput("Anna lukuvinkin otsikko: ");
    }

    @Test
    public void uiTallentaaVinkin() {
        when(console.readInput("Anna lukuvinkin otsikko: ")).thenReturn("Raamattu");
        ui.run();
        try {
            verify(dao).saveToFile(FILE, vinkki);
        } catch (Exception e) {
        }
    }

    @Test
    public void uiTulostaaVinkin() {
        when(console.readInput("Anna lukuvinkin otsikko: ")).thenReturn("Raamattu");
        ui.run();
        verify(console).printOutput("Luotiin lukuvinkki: Raamattu");
    }
}
