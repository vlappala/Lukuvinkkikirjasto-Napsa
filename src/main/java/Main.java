
import dao.LukuvinkkiDao;
import io.ConsoleIO;
import ui.ConsoleUI;
import ui.ConsoleUIbeta;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        ConsoleIO console = new ConsoleIO();
        LukuvinkkiDao dao = new LukuvinkkiDao();

        ConsoleUI ui = new ConsoleUI(console, dao);
//        ConsoleUI ui = new ConsoleUIbeta(console, dao);
        ui.run();
    }

}
