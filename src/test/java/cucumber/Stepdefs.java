package cucumber;

import dao.LukuvinkkiDao;
import domain.Kirja;
import domain.Lukuvinkki;
import io.ConsoleIO;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import org.junit.After;
import org.mockito.ArgumentCaptor;
import ui.ConsoleUI;

public class Stepdefs {
    
    ConsoleIO mockIO;
    LukuvinkkiDao mockDao;
    ConsoleUI ui;
    UiValues vals;
    Kirja vinkki;
    
    @Before
    public void setUp() throws IOException {
        mockIO = mock(ConsoleIO.class);
        mockDao = mock(LukuvinkkiDao.class);
        mockDao.useTestFile();
        ui = new ConsoleUI(mockIO, mockDao);
        vals = new UiValues();
        vinkki = new Kirja("Learn Java");
    }

    // lisäyksen stepdefs

    @Given("konsoli pyytaa lukuvinkin otsikkoa")
    public void konsoliPyytaaOtsikkoa() {
        mockIO = mock(ConsoleIO.class);
        mockDao = mock(LukuvinkkiDao.class);
        vinkki = new Kirja("Learn Java");
        ui = new ConsoleUI(mockIO, mockDao);
        vals = new UiValues();
    }

    @When("kelvollinen otsikko {string} syotetaan konsoliin")
    public void
    kelvollinenOtsikkoSyotetaan(String otsikko) throws IOException {
        otsikkoSyotetaan(otsikko);
    }

    @When("tyhja otsikko syotetaan konsoliin")
    public void tyhjaOtsikkoSyotetaan() throws IOException {
        String tyhja = "";
        otsikkoSyotetaan(tyhja);
    }

    @Then("konsoli vastaa halutulla viestilla")
    public void konsoliVastaaViestilla() {

        // Myöhempää käyttöä varten vaihtoehtoisia tapoja...
        // String oikea = vinkki.changeTimeToString(vinkki.getAddDateTime()) + " tallennettiin lukuvinkki: Testiotsikko URL: NIL, tägit: [tagi1, tagi2]\n";
        // verify(mockIO, times(1)).printOutput(oikea);
        // verify(mockIO, atLeastOnce()).printOutput(oikea);
        verify(mockIO).printOutput(eq(vinkki.changeTimeToString(vinkki.getAddDateTime()) + " tallennettiin lukuvinkki: Learn Java URL: NIL, tägit: [tagi1, tagi2]\n"));

    }

    @Then("lukuvinkki ei tallennu tiedostoon")
    public void
    lukuvinkkiEiTallennuTiedostoon() throws IOException {
        verify(mockDao, times(0)).saveToFile(new Kirja(anyString()));
    }

    public void otsikkoSyotetaan(String otsikko) throws IOException {
        when(mockIO.readInput(vals.mainMenu)).thenReturn("u", "l");
        when(mockIO.readInput(vals.otsikonPyynto)).thenReturn(otsikko);
        when(mockIO.readInput(vals.linkinPyynto)).thenReturn("");
        when(mockIO.readInput(vals.tagienPyynto)).thenReturn("tagi1;tagi2");

        ui.run();

    }
    
    @Given("on olemassa lukuvinkki nimelta {string}")
    public void lukuvinkkiOnOlemassa(String otsikko) throws IOException {

        when(mockIO.readInput(vals.mainMenu)).thenReturn(vals.uusiVal, vals.lopetusVal);
        when(mockIO.readInput(vals.otsikonPyynto)).thenReturn(otsikko);
        when(mockIO.readInput(vals.linkinPyynto)).thenReturn("");
        when(mockIO.readInput(vals.tagienPyynto)).thenReturn("tagi1;tagi2");
        
        ui.run();
        
    }

    @Given("on olemassa lukuvinkit nimelta {string} ja {string}")
    public void lukuvinkitOvatOlemassa(String otsikko1, String otsikko2) throws IOException {

        when(mockIO.readInput(vals.mainMenu)).thenReturn(vals.uusiVal, vals.uusiVal, vals.lopetusVal);
        when(mockIO.readInput(vals.otsikonPyynto)).thenReturn(otsikko1, otsikko2);
        when(mockIO.readInput(vals.linkinPyynto)).thenReturn("");
        when(mockIO.readInput(vals.tagienPyynto)).thenReturn("tagi1;tagi2");

        ui.run();

    }

    @Given("ei ole olemassa lukuvinkkia nimelta {string}")
    public void lukuvinkkiaEiOleOlemassa(String otsikko) throws IOException {

    }

    //haun stepdefs
    
    @When("kayttaja hakee lukuvinkkia hakusanalla {string}")
    public void lukuvinkkiaHaetaanHakusanalla(String hakusana) throws IOException {
        
        when(mockIO.readInput(vals.mainMenu)).thenReturn(hakusana, vals.lopetusVal);
        when(mockIO.readInput(vals.naytaLukuvinkkiMenu)).thenReturn(vals.returnVal);

        ui.run();
    
    }
    
    @Then("lukuvinkki {string} naytetaan konsolissa")
    public void lukuvinkkiNaytetaanKonsolissa(String otsikko) throws IOException {

        String expected = otsikko + " URL: NIL";

        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);

        verify(mockIO, atLeastOnce()).printOutput(captor.capture());

        List<String> values = captor.getAllValues();

        boolean expectedwasFound = false;
        for (String val : values) {
            if (val.contains(expected)) {
                expectedwasFound = true;
            }
        }

        assertTrue(expectedwasFound);
        
    }

    @Then("molemmat lukuvinkit {string} ja {string} naytetaan konsolissa")
    public void molemmatLukuvinkitNaytetaanKonsolissa(String otsikko1, String otsikko2) {

        String expected1 = otsikko1 + " URL: NIL";
        String expected2 = otsikko2 + " URL: NIL";

        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);

        verify(mockIO, atLeastOnce()).printOutput(captor.capture());

        List<String> values = captor.getAllValues();

        boolean expected1wasFound = false;
        boolean expected2wasFound = false;
        for (String val : values) {
            if (val.contains(expected1)) {
                expected1wasFound = true;
            }
            if (val.contains(expected2)) {
                expected2wasFound = true;
            }
        }

        assertTrue(expected1wasFound && expected2wasFound);

    }

    @Then("lukuvinkkia {string} ei loydy")
    public void yhtaanLukuvinkkiaEiLoydy(String otsikko) {

        String expected = "Ei löytynyt: " + otsikko;

        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);

        verify(mockIO, atLeastOnce()).printOutput(captor.capture());

        List<String> values = captor.getAllValues();

        boolean expectedwasFound = false;
        for (String val : values) {
            if (val.contains(expected)) {
                expectedwasFound = true;
            }
        }

        assertFalse(expectedwasFound);

    }

    //listauksen stepdefs

    @Given("ei ole olemassa yhtaan lukuvinkkia")
    public void eiOleLukuvinkkeja() {

    }

    @When("kayttaja listaa lukuvinkit listauskomennolla")
    public void lukuvinkitListataan() throws IOException {

        when(mockIO.readInput(vals.mainMenu)).thenReturn(vals.kaikkiVal, vals.lopetusVal);

        ui.run();

    }

    @Then("lukuvinkki {string} naytetaan listassa")
    public void lukuvinkkiNaytetaanListassa(String otsikko) throws IOException {

        String expected = otsikko;

        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);

        verify(mockIO, atLeastOnce()).printOutput(captor.capture());

        List<String> values = captor.getAllValues();

        boolean expectedwasFound = false;
        for (String val : values) {
            if (val.contains(expected)) {
                expectedwasFound = true;
            }
        }

        assertTrue(expectedwasFound);

    }

    @Then("molemmat lukuvinkit {string} ja {string} naytetaan listassa")
    public void molemmatLukuvinkitNaytetaanListassa(String otsikko1, String otsikko2) {

        String expected1 = otsikko1 + " URL: NIL";
        String expected2 = otsikko2 + " URL: NIL";

        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);

        verify(mockIO, atLeastOnce()).printOutput(captor.capture());

        List<String> values = captor.getAllValues();

        boolean expected1wasFound = false;
        boolean expected2wasFound = false;
        for (String val : values) {
            if (val.contains(expected1)) {
                expected1wasFound = true;
            }
            if (val.contains(expected2)) {
                expected2wasFound = true;
            }
        }

        assertTrue(expected1wasFound && expected2wasFound);

    }

    @Then("lista on tyhja")
    public void listaOnTyhja() {

        String expected = "Valitse vinkki numerolla tai";

        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);

        verify(mockIO, atLeastOnce()).printOutput(captor.capture());

        List<String> values = captor.getAllValues();

        boolean expectedwasFound = false;
        for (String val : values) {
            if (val.contains(expected)) {
                expectedwasFound = true;
            }
        }

        assertTrue(expectedwasFound);

    }

    //poiston stepdefs


    @When("kayttaja poistaa lukuvinkin {string} poistokomennolla")
    public void kayttajaPoistaaLukuvinkin(String otsikko) throws IOException {

        when(mockIO.readInput(vals.mainMenu)).thenReturn(vals.uusiVal,"1", vals.lopetusVal);
        when(mockIO.readInput(vals.naytaLukuvinkkiMenu)).thenReturn(vals.poistoVal);

        ui.run();

    }

    @Then("lukuvinkki {string} poistetaan")
    public void lukuvinkkiPoistetaan(String otsikko) {

        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(mockIO, atLeastOnce()).printOutput(captor.capture());


        String expected = "Poistettiin: " + otsikko;

        List<String> values = captor.getAllValues();

        boolean expectedwasFound = false;
        for (String val : values) {
            if (val.contains(expected)) {
                expectedwasFound = true;
            }
        }

        assertTrue(expectedwasFound);

    }

}
