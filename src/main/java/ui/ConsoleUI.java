package ui;

import domain.Kirja;
import domain.Lukuvinkki;
import dao.LukuvinkkiDao;
import domain.Etsija;
import io.ConsoleIO;
import java.io.*;
import java.util.*;

public class ConsoleUI {

    private final ConsoleIO console;
    private final LukuvinkkiDao dao;
    private List<Lukuvinkki> vinkit;

    public ConsoleUI(ConsoleIO console, LukuvinkkiDao dao) {
        this.console = console;
        this.dao = dao;
    }

    private void lueVinkit() throws IOException, FileNotFoundException {
        try {
            vinkit = dao.readFromFile();
        } catch (Exception e) {
            vinkit = new ArrayList<>();
        }
    }

    public void run() throws IOException, FileNotFoundException {
        String syote;

        lueVinkit();
        while (true) {
            // Lue syöte. Teksti tarkoittaa hakua, numero jotain toimintoa
            syote = console.readInput("(L)opeta (K)aikki (U)usi tai \nkirjoita useampi merkki hakua varten");
            int numero = -1;
            boolean haku = false;
            try {
                numero = Integer.parseInt(syote) - 1; // Valinnat alkavat ykkösestä
            } catch (Exception e) {
                syote = syote.toLowerCase().trim();
                if (syote.length() > 1) {
                    haku = true;
                }
            }

            // Toiminnon valinta. Virheelliset putoavat läpi.
            if (numero >= 0 && numero < vinkit.size()) {
                nayta(vinkit.get(numero));
            } else if (haku) {
                Etsija e = new Etsija(vinkit);
                List<Lukuvinkki> tulos = e.etsiVinkinNimella(syote);
                if (tulos.isEmpty()) {        // Ei löytynyt
                    console.printOutput("Ei löytynyt: " + syote);
                } else if (tulos.size() == 1) { // Näytetään ainut
                    nayta(tulos.get(0));
                } else {                        // Lista tuloksista
                    vinkit = tulos;
                    lista();
                }
            } else if ("u".equals(syote)) { // Lisäys
                Kirja uusi = new Kirja("");
                vinkit.add(uusi);
                muokkaa(uusi);
            } else if ("k".equals(syote)) { // Näytä kaikki
                lueVinkit();
                lista();
            } else if ("l".equals(syote)) { // Lopetus
                break;
            }
        }
    }

    public void lista() {
        int i = 0;
        for (Lukuvinkki v : vinkit) {
            i++;
            console.printOutput(String.format("%2d  %-20.20s  %s", i, v.getLabel(), v.getAddTime()));
        }
        console.printOutput("\nValitse vinkki numerolla tai");
    }

    public void nayta(Lukuvinkki v) throws IOException {

        console.printOutput("\n" + v.toString() + "\n");    // Tästä voisi tehdä vähän näyttävämmän
        String syote = console.readInput("<RET>Takaisin (M)uokkaa (P)oista").toLowerCase().trim();

        if (syote.equals("m")) {
            muokkaa(v);
        } else if (syote.equals("p")) {
            poista(v);
        }
    }

    public void muokkaa(Lukuvinkki v) throws IOException, FileNotFoundException {
        if (v.getLabel() != null) {
            console.printOutput("Lukuvinkin otsikko on " + v.getLabel());
        }
        String syote = console.readInput("Anna lukuvinkin otsikko: ").trim();
        syote = (syote.length()) == 0 ? v.getLabel() : syote;
        if (syote.length() > 0) {   // Ei luoda tyhjää vinkkiä
            v.setLabel(syote);
            // Sama linkille
            String linkki = (v.getLink() == null) ? "" : v.getLink();
            if (linkki.length() > 0) {
                console.printOutput("Lukuvinkin linkki on " + linkki);
            }
            syote = console.readInput("Anna lukuvinkin linkki: ");
/////////////////////////////////////////////////////////////////////////////
// Tässä pitää testata onko syötetty URL kelvollinen, muuten asettaa syote=""
/////////////////////////////////////////////////////////////////////////////
            syote = (syote.length()) == 0 ? linkki : syote;
            v.setLink(syote.trim());
            // Tallennus
/////////////////////////////////////////////////////////////////////////////
// Tässä pitää päivittää v:n muokkauspäiväys
/////////////////////////////////////////////////////////////////////////////
            dao.saveListToFile(vinkit);
            console.printOutput(v.getAddTime() + " tallennettiin lukuvinkki: " + v + "\n");
        }
    }

    public void poista(Lukuvinkki v) throws IOException, FileNotFoundException {
        vinkit.remove(v);
        dao.deleteFromFile(v);
        console.printOutput("Poistettiin: " + v.getLabel() + "\n");
    }

}
