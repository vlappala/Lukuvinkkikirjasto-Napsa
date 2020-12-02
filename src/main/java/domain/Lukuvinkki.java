package domain;

import java.time.LocalDateTime;

import java.lang.reflect.Type;
import com.google.gson.InstanceCreator;
import java.time.format.DateTimeFormatter;

public class Lukuvinkki implements InstanceCreator<Lukuvinkki> {

    private String label;
    // lukuvinkin tyyppi esim. kirja
    private String type;
    // esimerkkimuuttuja lisäyspäivämäärälle
    private LocalDateTime addDateTime;
    private String link;

    public Lukuvinkki(String label) {
        this.label = label;
        this.addDateTime = LocalDateTime.now();
        this.link = "NIL";

    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getLink() {
        return this.link;
    }

    public LocalDateTime getAddDateTime() {
        return this.addDateTime;
    }

    public String getAddTime() {
        return this.changeTimeToString(addDateTime);
    }

    @Override
    public String toString() {
        return this.label + " URL: " + this.link;
    }

    @Override
    public Lukuvinkki createInstance(Type type) {
        return new Lukuvinkki(label);
    }

    public String changeTimeToString(LocalDateTime time) {
        return this.addDateTime.format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
    }

}
