package domain;

import java.time.LocalDateTime;

import java.lang.reflect.Type;
import com.google.gson.InstanceCreator;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Lukuvinkki implements InstanceCreator<Lukuvinkki> {

    private String label;
    // lukuvinkin tyyppi esim. kirja
    private String type;
    private String link;
    private URL linkki;


    private ArrayList<String> tagit;


    private LocalDateTime addDateTime;
    private LocalDateTime modifiedDateTime;
    

    public Lukuvinkki(String label) {
        this.label = label;
        this.addDateTime = LocalDateTime.now();
        this.modifiedDateTime = this.addDateTime;
        this.link = "EMPTY";
        this.linkki = null;
        this.tagit = new ArrayList<>();

    }

    public void setLabel(String label) {
        this.label = label;
    }
    
    public void setModifiedDateTime() {
        this.modifiedDateTime = LocalDateTime.now();
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

    public void setLinkki(URL linkki) {
        this.linkki = linkki;
    }

    public String getLink() {
        return this.link;
    }

    public URL getLinkki() {
        return this.linkki;
    }

    public void addTagi(String tagi) {
        if (!this.tagit.contains(tagi)) {
            this.tagit.add(tagi);
        }
        //this.tagit.add(tagi);
    }
    
    public List getTagit() {
        return this.tagit;
    }

    public LocalDateTime getAddDateTime() {
        return this.addDateTime;
    }

    public String getAddTime() {
        return this.changeTimeToString(this.getAddDateTime());
    }
    
    public LocalDateTime getModifiedDateTime() {
        return this.modifiedDateTime;
    }
    
    public String getModifiedTime() {
        return this.changeTimeToString(this.getModifiedDateTime());
    }

    @Override
    public String toString() {
        if (this.linkki == null) {
            return this.label + " URL: NIL" + ", tägit: " + this.tagit;
        } else {
            return this.label + " URL: " + this.linkki + ", tägit: " + this.tagit;
        }
        //return this.label + " URL: " + this.link;
    }

    @Override
    public Lukuvinkki createInstance(Type type) {
        return new Lukuvinkki(label);
    }

    public String changeTimeToString(LocalDateTime time) {
        return time.format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
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
