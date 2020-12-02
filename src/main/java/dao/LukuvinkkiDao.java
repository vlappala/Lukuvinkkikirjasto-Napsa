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

public class LukuvinkkiDao {

    String filePath = "./Vinkit";
    Gson gson = new Gson();
    List<Lukuvinkki> vinkit;
    File newFile;
    Scanner scanner;
    Boolean testFile = false;

    public void saveToFile(Lukuvinkki content) throws IOException {
        
        
        newFile = new File(filePath + ".txt");
        newFile.createNewFile();

        if (testFile) {
            newFile.deleteOnExit();
        }

        if (readFromFile() == null) {
            vinkit = new ArrayList<Lukuvinkki>();
            
            vinkit.add(content);
        }

        else {

            vinkit = readFromFile();

            vinkit.add(content);
        }


        String output = gson.toJson(vinkit);
    
        FileOutputStream oFile = new FileOutputStream(newFile, false); 
        FileWriter fileWriter = new FileWriter(newFile);
        fileWriter.write(output + "\n");
        
        fileWriter.close();
   
    }

    public void saveListToFile(List<Lukuvinkki> content) throws IOException {
        
        
        newFile = new File(filePath + ".txt");
        newFile.createNewFile();

        if (testFile) {
            newFile.deleteOnExit();
        }

        String output = gson.toJson(content);
    
        FileOutputStream oFile = new FileOutputStream(newFile, false); 
        FileWriter fileWriter = new FileWriter(newFile);
        fileWriter.write(output + "\n");
        
        fileWriter.close();
   
    }

    public List<Lukuvinkki> readFromFile() throws FileNotFoundException, IOException {
        
       
        String content = "";
        

        
        FileInputStream input = new FileInputStream(filePath + ".txt");
        scanner = new Scanner(input);
       
        
        
        
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

    public void useTestFile() throws IOException, FileNotFoundException {
        filePath = "./Test";
        testFile = true;
    }

}

