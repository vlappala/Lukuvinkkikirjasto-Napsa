package dao;


import java.lang.reflect.Type; 
import java.io.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;  
import java.util.Scanner;
import java.util.List;



import domain.Lukuvinkki;

public class LukuvinkkiDao {

    String filePath = "./";
    Gson gson = new Gson();
    List<Lukuvinkki> vinkit;
    

    public void saveToFile(String filename, List<Lukuvinkki> content) throws IOException {
        
        String output = gson.toJson(content);
        File newFile = new File(filePath + filename + ".txt");
        newFile.createNewFile();
        FileOutputStream oFile = new FileOutputStream(newFile, false); 
        FileWriter fileWriter = new FileWriter(newFile);
        fileWriter.write(output + "\n");
        
        fileWriter.close();
    }

    public List<Lukuvinkki> readFromFile(String filename) throws FileNotFoundException, IOException {
        
       

        FileInputStream input = new FileInputStream(filePath + filename + ".txt");
        Scanner scanner = new Scanner(input);
       
        String content = "";
        
        
        if (!scanner.hasNextLine()) {
            return null;
        }
        
        while (scanner.hasNextLine()) {
            
            content = content + scanner.nextLine();
        }

        scanner.close();

        List<Lukuvinkki> fileContent = gson.fromJson(content, new TypeToken<List<Lukuvinkki>>() {}.getType());

        
        
        return fileContent;

    }

}
