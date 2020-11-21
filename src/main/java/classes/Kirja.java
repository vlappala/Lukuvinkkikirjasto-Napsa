package classes;

import interfaces.Lukuvinkki;

public class Kirja implements Lukuvinkki {
    
    private String label;

    public Kirja(String label) {
        this.label = label;
    }
    
    public void setLabel(String label) {
        this.label = label;
    }
    
    public String getLabel() {
        return this.label;
    }
    
    @Override
    public String toString() {
        return label;
    }

}