package domain;

import java.time.LocalDateTime;

import java.lang.reflect.Type;
import com.google.gson.InstanceCreator;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

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
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        final Lukuvinkki other = (Lukuvinkki) obj;
        if (!Objects.equals(this.label, other.label)) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        if (!Objects.equals(this.link, other.link)) {
            return false;
        }
        if (!Objects.equals(this.addDateTime, other.addDateTime)) {
            return false;
        }
        return true;
    }
    
    

}
