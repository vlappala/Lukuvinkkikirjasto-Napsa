package ui;

import domain.Kirja;
import domain.Lukuvinkki;
import dao.LukuvinkkiDao;
import domain.Etsija;
import io.ConsoleIO;
import java.io.File;
import java.util.List;
import java.util.ArrayList;

public class ConsoleUI {

    private final String FILE = "Vinkit";   // Tämä kuuluisi DAOon
    private ConsoleIO console;
    private LukuvinkkiDao dao;
    private List<Lukuvinkki> vinkit;

    public ConsoleUI(ConsoleIO console, LukuvinkkiDao dao) {
        this.console = console;
        this.dao = dao;
    }

    private void lueVinkit() {
        try {
            vinkit = dao.readFromFile(FILE);
        } catch (Exception e) {
            console.printOutput("VIRHE: " + e.getMessage());
            vinkit = new ArrayList<Lukuvinkki>();
        }
    }

    public void run() {
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
            console.printOutput("99 < Lopeta ohjelma >");

            // Lue syöte. Teksti tarkoittaa hakua, numero jotain toimintoa
            String syote = console.readInput("\nValitse vinkki numerolla tai kirjoita teksti hakua varten:");
            int numero = -1;
            boolean haku = true;
            try {
                numero = Integer.parseInt(syote);
                haku = false;
            } catch (Exception e) {
            }

            // Toiminnon valinta. Virheelliset putoavat läpi ja tulostuu uusi lista.
            if (haku && !syote.isBlank()) {         // Haku
                Etsija e = new Etsija(vinkit);
                vinkit = e.etsiVinkinNimella(syote);
                rajattu = true;
                continue;
            } else if (numero == 0 && !rajattu) {   // Lisäys
                lisaa();
                continue;
            } else if (numero == 0 && rajattu) {    // Haun poisto
                lueVinkit();
                rajattu = false;
                continue;
            } else if (numero > 0 && numero <= vinkit.size() + 1) {
                nayta(vinkit.get(numero - 1)); // Valinnat alkavat ykkösestä
                continue;
            } else if (numero == 99) {              // Lopetus
                break;
            }
        }
    }

    public void nayta(Lukuvinkki v) {
        console.printOutput("\n" + v.toString());
    }

    public void lisaa() {
        Kirja kirja = new Kirja(console.readInput("\nAnna lukuvinkin otsikko: "));
        if (kirja.toString() != null && !kirja.toString().isBlank()) {
            try {
                dao.saveToFile(FILE, kirja);
                vinkit = dao.readFromFile(FILE);
            } catch (Exception e) {
                console.printOutput("VIRHE: " + e.getMessage());
            }
            console.printOutput(kirja.getAddTime() + " Luotiin lukuvinkki: " + kirja);
        }
    }

    public String getFileName() {   // Tämä kuuluisi DAOon
        return this.FILE;
    }

}
