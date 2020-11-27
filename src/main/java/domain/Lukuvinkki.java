package domain;

import java.util.Date;

import java.lang.reflect.Type;
import com.google.gson.InstanceCreator;

public class Lukuvinkki implements InstanceCreator<Lukuvinkki>{
    
    private String label;
    // lukuvinkin tyyppi esim. kirja
    private String type;
    // esimerkkimuuttuja lisäyspäivämäärälle
    private Date addDate;
    
    public Lukuvinkki(String label) {
        this.label = label;
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
    
    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }
    
    public Date getAddDate() {
        return this.addDate;
    }

    @Override
    public String toString() {
        return this.label;
    }

    @Override
    public Lukuvinkki createInstance(Type type) {
        return new Lukuvinkki(label);
    }

}
