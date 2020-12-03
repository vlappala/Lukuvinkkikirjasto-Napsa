package ui;

import dao.LukuvinkkiDao;
import domain.Kirja;
import java.io.*;
import domain.Lukuvinkki;
import io.ConsoleIO;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class ConsoleUITest {

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
    
    @Test(timeout = 1000) public void uiKysyyToiminnon() throws IOException, FileNotFoundException {
     
        when(console.readInput("\nValitse vinkki numerolla tai kirjoita teksti hakua varten:")).thenReturn("99");
        
        ui.run(); 
        
        verify(console, times(1)).readInput(anyString());
    }
    
    @Test(timeout = 1000) public void uiTallentaaVinkin() throws IOException {
        try { when(dao.readFromFile()).thenReturn(vinkit); } catch (Exception e) { }
        when(console.readInput("\nValitse vinkki numerolla tai kirjoita teksti hakua varten:")).
        thenReturn("0", "99");
        
        when(console.readInput("\nAnna lukuvinkin otsikko: ")).thenReturn("Raamattu");
        ui.run(); 
        //dao.saveToFile(vinkki); 
        verify(dao, times(2)).readFromFile();

    }
     
    @Test(timeout = 1000) public void uiTulostaaVinkin() throws IOException, FileNotFoundException { 
        try { when(dao.readFromFile()).thenReturn(vinkit); 
        } catch (Exception e) { 
        } 
        when(console.readInput("\nValitse vinkki numerolla tai kirjoita teksti hakua varten:")).thenReturn("0", "99");
        
        when(console.readInput("\nAnna lukuvinkin otsikko: ")).thenReturn("Raamattu");
        ui.run(); 
        
        verify(console).printOutput(vinkki.changeTimeToString(vinkki.getAddDateTime()) +
        " Luotiin lukuvinkki: Raamattu URL: NIL"); }
    
    }
