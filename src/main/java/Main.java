import java.util.*;
import classes.Kirja;
import io.ConsoleIO;
import dao.LukuvinkkiDao;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        ConsoleIO console = new ConsoleIO();
        LukuvinkkiDao dao = new LukuvinkkiDao();
        

        Kirja abc = new Kirja(console.readInput("Anna lukuvinkin otsikko: "));
        console.printOutput("Luotiin lukuvinkki: " + abc.toString());

        dao.saveToFile(abc.toString(), abc.toString());
        




    }

}