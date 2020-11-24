package io;

import java.util.Scanner;

public class ConsoleIO {

    private Scanner scanner = new Scanner(System.in);

    public void printOutput(String output) {
        System.out.println(output);
    }

    public String readInput(String promptForUser) {
        System.out.println(promptForUser);
        return scanner.nextLine();
    }
}
