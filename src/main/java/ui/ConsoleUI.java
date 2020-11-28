package ui;

import domain.Kirja;
import domain.Lukuvinkki;
import dao.LukuvinkkiDao;
import io.ConsoleIO;
import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class ConsoleUI {

    private final String FILE = "Vinkit";
    private ConsoleIO console;
    private LukuvinkkiDao dao;
    private List<Lukuvinkki> vinkit;
    private File baseFile;

    public ConsoleUI(ConsoleIO console, LukuvinkkiDao dao) {
        this.console = console;
        this.dao = dao;

    }

    public void run() {
        Kirja kirja = new Kirja(console.readInput("Anna lukuvinkin otsikko: "));

        if (kirja.toString() != null && !kirja.toString().isEmpty()) {

            try {

                dao.saveToFile(FILE, kirja);

                vinkit = dao.readFromFile(FILE);

            } catch (Exception e) {
                console.printOutput("VIRHE: " + e.getMessage());
            }

            console.printOutput(vinkit.get(vinkit.size() - 1).getAddTime() + " Luotiin lukuvinkki: " + vinkit.get(vinkit.size() - 1));

        }

    }

    public String getFileName() {
        return this.FILE;
    }

}
