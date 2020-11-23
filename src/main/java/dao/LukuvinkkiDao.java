package dao;


import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;

public class LukuvinkkiDao {

    String filePath = "./";
    

    public void saveToFile(String filename, String content) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath + filename + ".txt", true);
        fileWriter.write(content + "\n");
        fileWriter.close();
    }

    public String readFromFile(String filename) throws FileNotFoundException, IOException {
        FileReader fileReader = new FileReader(filePath + filename + ".txt");
        String fileContent = "";
        int ch = fileReader.read();

        while(ch != -1) {
            fileContent = fileContent + (char)ch;
            fileReader.close();
        }

        return fileContent;

    }

}
