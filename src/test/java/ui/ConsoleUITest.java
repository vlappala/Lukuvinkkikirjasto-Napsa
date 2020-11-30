package ui;

import dao.LukuvinkkiDao;
import domain.Kirja;
import java.io.*;
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
    public void uiKysyyVinkin() throws IOException, FileNotFoundException {
        ui.run();
        verify(console).readInput("Anna lukuvinkin otsikko: ");
    }

    @Test
    public void uiTallentaaVinkin() throws IOException {
        when(console.readInput("Anna lukuvinkin otsikko: ")).thenReturn("Raamattu");
        ui.run();
        dao.saveToFile(vinkki);
        verify(dao).readFromFile();
        
    }

    @Test
    public void uiTulostaaVinkin() throws IOException {
        when(console.readInput("Anna lukuvinkin otsikko: ")).thenReturn("Raamattu");
        ui.run();
        dao.saveToFile(vinkki);

        verify(console).printOutput(vinkki.changeTimeToString(vinkki.getAddDateTime()) + " Luotiin lukuvinkki: Raamattu");
    }
}
