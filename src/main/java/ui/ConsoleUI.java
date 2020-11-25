
package ui;

import domain.Kirja;
import domain.Lukuvinkki;
import dao.LukuvinkkiDao;
import io.ConsoleIO;
import java.util.List;
import java.util.Arrays;

public class ConsoleUI {

    private final String FILE = "Vinkit";
    private ConsoleIO console;
    private LukuvinkkiDao dao;
    private String uusiLukuvinkki;
    private Kirja[] content;
    private List<Kirja> vinkit;
    
    

    public ConsoleUI(ConsoleIO console, LukuvinkkiDao dao) {
        this.console = console;
        this.dao = dao;
        
       
        
    }

    public void run() {
        Kirja kirja = new Kirja(console.readInput("Anna lukuvinkin otsikko: "));
        String loppuTulostus = "";
        
        if (kirja.toString() != null && !kirja.toString().isEmpty()) {
            try {

                    //Ensin luetaan Vinkit.txt, vanhat tiedot otetaan talteen listaan
                    vinkit = dao.readFromFile(FILE);

                    //Uusi olio lisätään listaan
                    vinkit.add(kirja);

                    //Lista työnnetään JSON-muodossa tiedostoon, samalla ylikirjoittaen
                    dao.saveToFile(FILE, vinkit);

                    //Kikkailua
                    int vika = dao.readFromFile(FILE).size() - 1;
                    loppuTulostus = dao.readFromFile(FILE).get(vika).toString();

                    
                
                
               

            } catch (Exception e) {
                console.printOutput("VIRHE: " + e.getMessage());
            }
            
            console.printOutput("Luotiin lukuvinkki: " + loppuTulostus);
        }
    }
    
    public String getFileName() {
        return this.FILE;
    }
}
