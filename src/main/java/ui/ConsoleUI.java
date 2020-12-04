package ui;

import domain.Kirja;
import domain.Lukuvinkki;
import dao.LukuvinkkiDao;
import domain.Etsija;
import io.ConsoleIO;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConsoleUI {

    private ConsoleIO console;
    private LukuvinkkiDao dao;
    private List<Lukuvinkki> vinkit;
    String syote;
    

    public ConsoleUI(ConsoleIO console, LukuvinkkiDao dao) {
        this.console = console;
        this.dao = dao;
        syote = "";
    }

    private void lueVinkit() throws IOException, FileNotFoundException {
        try {
            vinkit = dao.readFromFile();
            if (vinkit == null) {
                vinkit = new ArrayList<Lukuvinkki>();
            }
        } catch (Exception e) {
            console.printOutput("VIRHE: " + e.getMessage());
        }
    }

    public void run() throws IOException, FileNotFoundException {
        boolean rajattu = false;

        lueVinkit();
        while (true) {
            
            // Näytä lista. Eka rivi riippuu siitä, onko haku päällä vai ei
            if (!rajattu) {
                console.printOutput("\n 0 < Luo uusi vinkki >");
            } else {
                console.printOutput("\n 0 < Poista haku >");
            }
            int i = 0;
            for (Lukuvinkki v : vinkit) {
                i++;
                console.printOutput(String.format("%2d  %-20.20s  %s", i, v.getLabel(), v.getAddTime()));
            }
            console.printOutput("999 < Lopeta ohjelma >");

            // Lue syöte. Teksti tarkoittaa hakua, numero jotain toimintoa
            syote = console.readInput("\nValitse vinkki numerolla tai kirjoita teksti hakua varten:");
            int numero = -1;
            boolean haku = true;

            try {
                numero = Integer.parseInt(syote);
                haku = false;
            } catch (Exception e) {
            }

            // Toiminnon valinta. Virheelliset putoavat läpi ja tulostuu uusi lista.
            if (haku && !syote.trim().isEmpty()) { // Haku
                Etsija e = new Etsija(vinkit);
                vinkit = e.etsiVinkinNimella(syote);
                rajattu = true;
                continue;
            } else if (numero == 0 && !rajattu) { // Lisäys
                lisaa();
                continue;
            } else if (numero == 0 && rajattu) { // Haun poisto
                lueVinkit();
                rajattu = false;
                continue;
            } else if (numero > 0 && numero <= vinkit.size()) {
                nayta(vinkit.get(numero - 1), (numero - 1)); // Valinnat alkavat ykkösestä
                continue;
            } else if (numero == 999) { // Lopetus
                break;
            }
        }
    }

    public void nayta(Lukuvinkki v, int i) throws IOException {
        
        console.printOutput("\n" + v.toString());
        
        String valinta = console.readInput("\nMuokkaa vinkin linkkiä valitsemalla M"
                + "\nPoista linkki valitsemalla P"
                + "\nPalaa alkuvalikkoon valitsemalla 0");
        if (valinta.equals("M")) {
            // UI luonnos...
            lisaaLinkki(v);
            // Luonnos päättyy...
            
            //v.setLink(console.readInput("\nKirjoita URL: "));
            // Muokattu vinkki paikataan listassa
            String lisattavaTagi = console.readInput("Anna tagi");
            v.addTagi(lisattavaTagi);
            vinkit.set(i, v);
            dao.saveListToFile(vinkit);
        } else if (valinta.equals("P")) {
            poista(v);
        } else if (valinta.equals("0")) {
            console.printOutput("\nPoistuttiin.");
        } else {
            console.printOutput("\nValintaa ei tunnistettu");
        }
        
    }
    
    public void poista(Lukuvinkki v) {
        dao.deleteFromFile(v);
        vinkit = dao.readFromFile();
        console.printOutput("Poistettiin: " + v.getLabel());
    }

    public void lisaa() {
        Kirja kirja = new Kirja(console.readInput("\nAnna lukuvinkin otsikko: "));
        if (kirja.toString() != null && !kirja.toString().trim().isEmpty()) {
            try {
                dao.saveToFile(kirja);
                vinkit = dao.readFromFile();
            } catch (Exception e) {
                console.printOutput("VIRHE: " + e.getMessage());
            }
            console.printOutput(kirja.getAddTime() + " Luotiin lukuvinkki: " + kirja);
        }
    }
    
    public void luoVinkkiJaLinkki() {
        Lukuvinkki vinkki = new Lukuvinkki(this.console.readInput("Lukuvinkin otsikko: "));
        boolean linkkiValidi = false;
        while (!linkkiValidi) {
            try {
                String linkkiInput = this.console.readInput("Anna vinkkiin liittyvä linkki "
                        + "(voit myös jättää tyhjäksi): ");
                if (linkkiInput.equals("")) {
                    break;
                } else {
                    URL urli = new URL(linkkiInput);
                    linkkiValidi = true;
                    vinkki.setLinkki(urli);
                }
            } catch (MalformedURLException e) {
                this.console.printOutput("Antamasi linkki ei ole validi!");
            }
        }
        this.vinkit.add(vinkki);

    }

    public void lisaaLinkki(Lukuvinkki vinkki) {
        boolean linkkiValidi = false;
        while (!linkkiValidi) {
            try {
                String linkkiInput = this.console.readInput("Anna vinkkiin liittyvä linkki "
                        + "(voit myös jättää tyhjäksi): ");
                if (linkkiInput.equals("")) {
                    break;
                } else {
                    URL urli = new URL(linkkiInput);
                    linkkiValidi = true;
                    vinkki.setLinkki(urli);
                }
            } catch (MalformedURLException e) {
                this.console.printOutput("Antamasi linkki ei ole validi!");
            }
        }
    }
    
    

    

}
