package dao;

import java.lang.reflect.Type;
import java.io.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.Scanner;
import java.util.List;
import java.util.List;
import java.util.ArrayList;

import domain.Lukuvinkki;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LukuvinkkiDao {

    String filePath = "./Vinkit";
    Gson gson = new Gson();
    List<Lukuvinkki> vinkit;
    File newFile;
    Scanner scanner;
    Boolean testFile = false;

    public void saveToFile(Lukuvinkki content) {

        if (newFile == null) {
            createFile();
        }

        if (readFromFile() == null) {
            vinkit = new ArrayList<Lukuvinkki>();

            vinkit.add(content);
        } else {

            vinkit = readFromFile();

            vinkit.add(content);
        }

        String output = gson.toJson(vinkit);

        //FileOutputStream oFile = new FileOutputStream(newFile, false); 
        writeToFile(output);

    }

    public void deleteFromFile(Lukuvinkki content) {

        vinkit = readFromFile();

        if (vinkit != null) {
            vinkit.remove(content);

            String output = gson.toJson(vinkit);
            writeToFile(output);
        }

    }

    public void saveListToFile(List<Lukuvinkki> content) {

        if (newFile == null) {
            createFile();
        }

        String output = gson.toJson(content);

        //FileOutputStream oFile = new FileOutputStream(newFile, false); 
        writeToFile(output);

    }

    public List<Lukuvinkki> readFromFile() {

        String content = "";

        List<Lukuvinkki> fileContent = null;

        try {

            FileInputStream input = new FileInputStream(filePath + ".txt");
            scanner = new Scanner(input);

            if (!scanner.hasNextLine()) {
                return null;
            }

            while (scanner.hasNextLine()) {
                String next = scanner.nextLine();
                content = content + next;
            }

            scanner.close();

            fileContent = gson.fromJson(content, new TypeToken<List<Lukuvinkki>>() {
            }.getType());

            input.close();

        } catch (FileNotFoundException e) {
            fileContent = new ArrayList<>();
        } catch (IOException e) {
            System.out.println("VIRHE: " + e.getMessage());
        }

        return fileContent;

    }

    //apumetodi copypasten vähentämiseksi
    public void createFile() {

        newFile = new File(filePath + ".txt");
        try {
            newFile.createNewFile();
        } catch (IOException e) {
            System.out.println("VIRHE: " + e.getMessage());
        }

        if (testFile) {
            newFile.deleteOnExit();
        }

    }

    //apumetodi copypasten vähentämiseksi
    public void writeToFile(String content) {

        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter(newFile);
            fileWriter.write(content + "\n");
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("VIRHE: " + e.getMessage());
        }

    }

    public void useTestFile() throws IOException, FileNotFoundException {
        filePath = "./Test";
        testFile = true;
    }

}
