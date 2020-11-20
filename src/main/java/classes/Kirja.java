package classes;

public class Kirja {
    String label;
    String author;
    String ISBN;

    public Kirja(String label) {
        
        this.label = label;
        this.author = "NIL";
        this.ISBN = "NIL";

    }

    public String getLabel() {
        return this.label;
    }

    public String getAuthor() {
        return this.author;
    }

    public String getISBN() {
        return this.ISBN;
    }

    public String toString() {
        return label + ": Author: " + author + " , ISBN: " + ISBN;
    }

}