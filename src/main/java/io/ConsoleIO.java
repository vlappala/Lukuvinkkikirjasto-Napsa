package io;

import java.util.Scanner;

public class ConsoleIO implements IO {

    private Scanner scanner = new Scanner(System.in);

    @Override
    public void printOutput(String output) {
        System.out.println(output);
    }
    
    @Override
    public String readInput(String promptForUser) {
        System.out.println(promptForUser);
        return scanner.nextLine();
    }
}
