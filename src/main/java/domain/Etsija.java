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

}
