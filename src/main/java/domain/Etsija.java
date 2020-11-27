package domain;

import io.ConsoleIO;
import java.util.ArrayList;
import java.util.List;

public class Etsija {

    private List<Lukuvinkki> vinkit;

    public Etsija(List<Lukuvinkki> vinkit) {
        this.vinkit = vinkit;
    }

    public List<Lukuvinkki> etsiVinkinNimella(String hakusana) {
        ArrayList<Lukuvinkki> hakutulokset = new ArrayList<>();
        for (Lukuvinkki vinkki : this.vinkit) {
            if (vinkki.getLabel().contains(hakusana)) {
                hakutulokset.add(vinkki);
            }
        }
        return hakutulokset;
    }

    public static void main(String[] args) {
        // UI mockup luonnostelua...
        List<Lukuvinkki> vinkkeja = new ArrayList<>();
        vinkkeja.add(new Kirja("Seitsemän veljestä"));
        vinkkeja.add(new Kirja("Sinuhe"));
        vinkkeja.add(new Kirja("Learn C Programming"));
        Etsija ek = new Etsija(vinkkeja);
        ConsoleIO io = new ConsoleIO();
        while (true) {
            String hakusana = io.readInput("Anna hakusana (tyhjä merkkijono "
                    + "lopettaa ohjelman suorituksen)");
            if (hakusana.isEmpty()) {
                break;
            }
            List<Lukuvinkki> hakutulokset = ek.etsiVinkinNimella(hakusana);
            if (!hakutulokset.isEmpty()) {
                System.out.println("Löydettiin " + hakutulokset.size()
                        + " vinkkiä hakusanalla '" + hakusana + "'");
                for (Lukuvinkki tulos : hakutulokset) {
                    System.out.println(tulos);
                }
            } else {
                System.out.println("Hakusanalla ei löytynyt yhtään tulosta!");
            }

        }
    }

}
