package classes;

import interfaces.Lukuvinkki;

public class Kirja implements Lukuvinkki {
    
    private String label;
//    String author;
//    String ISBN;

    public Kirja(String label) {
        this.label = label;
//        this.author = "NIL";
//        this.ISBN = "NIL";
    }
    
    public void setLabel(String label) {
        this.label = label;
    }
    
    public String getLabel() {
        return this.label;
    }
//
//    public String getAuthor() {
//        return this.author;
//    }
//
//    public String getISBN() {
//        return this.ISBN;
//    }
    
    @Override
    public String toString() {
        return label;
    }

}