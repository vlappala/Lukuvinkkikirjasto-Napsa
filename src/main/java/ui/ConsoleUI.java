package ui;

import domain.Kirja;
import dao.LukuvinkkiDao;
import io.ConsoleIO;

public class ConsoleUI {

    private final String FILE = "Vinkit";
    private ConsoleIO console;
    private LukuvinkkiDao dao;

    public ConsoleUI(ConsoleIO console, LukuvinkkiDao dao) {
        this.console = console;
        this.dao = dao;
    }

    public void run() {
        Kirja kirja = new Kirja(console.readInput("Anna lukuvinkin otsikko: "));
        if (kirja.toString() != null && !kirja.toString().isEmpty()) {
            try {
                dao.saveToFile(FILE, kirja.toString());
            } catch (Exception e) {
                console.printOutput("VIRHE: " + e.getMessage());
            }
            console.printOutput("Luotiin lukuvinkki: " + kirja.toString());
        }
    }
    
    public String getFileName() {
        return this.FILE;
    }
}
