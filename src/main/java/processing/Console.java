package processing;

import java.io.*;
import java.util.Scanner;


public class Console {
    private CommandInvoker invoker;
    private CollectionHandler collectionHandler;

    public Console(CommandInvoker invoker, CollectionHandler collectionHandler) {
        this.invoker = invoker;
        this.collectionHandler = collectionHandler;
    }

    public void interactiveMode() {
        FileHandler.clearOutFile();
        FileHandler.clearUserErrFile();
        CommandParser parser = new CommandParser(invoker);
        Scanner in = new Scanner(System.in);
        while (true) {
            PrintStream printStream = new PrintStream(System.out);
            printStream.print("Type command and press Enter: ");
            String nextLine = in.nextLine();
            parser.commandProcessing(nextLine);
        }
    }

    public void insertMode(Long key) {

    }

    public static void printHelpMessage() {
        PrintStream printStream = new PrintStream(System.out);
        printStream.println("Type 'help' and press Enter to " +
                "see a list of commands");
    }

    public static String getHelpMessage() {
        return "Type 'help' and press Enter to " +
                "see a list of commands";
    }

    public static void println(String message) {
        PrintStream printStream = new PrintStream(System.out);
        printStream.println(message);
    }

    public static void print(String message) {
        PrintStream printStream = new PrintStream(System.out);
        printStream.print(message);
    }

    public static void printOutputFile() {
        println(FileHandler.readOutFile());
    }

    public static void printUserErrorsFile() {
        println(FileHandler.readUserErrFile());
    }
}
