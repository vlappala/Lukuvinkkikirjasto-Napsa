package cucumber;

import dao.LukuvinkkiDao;
import io.ConsoleIO;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import ui.ConsoleUI;

public class Stepdefs {
    
    ConsoleIO mockIO;
    LukuvinkkiDao mockLukuvinkkiDao;
    
    ConsoleUI ui;
    
    @Given("konsoli pyytaa lukuvinkin otsikkoa")
    public void konsoliPyytaaOtsikkoa() {
        
    }
    
    @When("kelvollinen otsikko {string} syotetaan konsoliin")
    public void kelvollinenOtsikkoSyotetaan(String otsikko) {
        mockIO = mock(ConsoleIO.class);
        mockLukuvinkkiDao = mock(LukuvinkkiDao.class);
        
        when(mockIO.readInput("Anna lukuvinkin otsikko: ")).thenReturn(otsikko);
        
        ui = new ConsoleUI(mockIO, mockLukuvinkkiDao);
        
        ui.run();
        
    }
    
    @Then("konsoli vastaa viestilla {string}")
    public void konsoliVastaaViestilla(String viesti) {
        verify(mockIO).printOutput(eq(viesti));
    }
    
}
