package dao;


import java.lang.reflect.Type; 
import java.io.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;  
import java.util.Scanner;
import java.util.List;


import domain.Kirja;

public class LukuvinkkiDao {

    String filePath = "./";
    Gson gson = new Gson();
    

    public void saveToFile(String filename, List<Kirja> content) throws IOException {
        
        String output = gson.toJson(content);
        
        FileWriter fileWriter = new FileWriter(filePath + filename + ".txt");
        fileWriter.write(output + "\n");
        
        fileWriter.close();
    }

    public List<Kirja> readFromFile(String filename) throws FileNotFoundException, IOException {
        
        FileInputStream input = new FileInputStream(filePath + filename + ".txt");
        Scanner scanner = new Scanner(input);
       
        String content = "";
        
        
        
        
        while (scanner.hasNextLine()) {
            
            content = content + scanner.nextLine();
        }

        scanner.close();

        List<Kirja> fileContent = gson.fromJson(content, new TypeToken<List<Kirja>>() {}.getType());

        
        return fileContent;

    }

}
