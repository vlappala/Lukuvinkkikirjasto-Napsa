package ui;

import domain.Kirja;
import domain.Lukuvinkki;
import dao.LukuvinkkiDao;
import io.ConsoleIO;
import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class ConsoleUI {

    
    private ConsoleIO console;
    private LukuvinkkiDao dao;
    private List<Lukuvinkki> vinkit;
    private File baseFile;

    public ConsoleUI(ConsoleIO console, LukuvinkkiDao dao) {
        this.console = console;
        this.dao = dao;

    }

    public void run() throws IOException, FileNotFoundException {
        Kirja kirja = new Kirja(console.readInput("Anna lukuvinkin otsikko: "));


        if (kirja.toString() != null && !kirja.toString().isEmpty()) {

        

                dao.saveToFile(kirja);

                vinkit = dao.readFromFile();

        

            console.printOutput(kirja.getAddTime() + " Luotiin lukuvinkki: " + kirja.toString());

        }

    }
    
}
