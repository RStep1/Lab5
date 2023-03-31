package processing;

import data.Vehicle;
import mods.ExecuteMode;
import utility.ValueHandler;
import utility.ValueTransformer;
import utility.Process;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;



public class Console {
    private CommandInvoker invoker;
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m";


    public Console(CommandInvoker invoker) {
        this.invoker = invoker;
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
            boolean exitStatus = parser.commandProcessing(nextLine, ExecuteMode.COMMAND_MODE);
            if (!exitStatus)
                break;
        }
    }


    public static Vehicle insertMode(long id, java.time.ZonedDateTime creationDate) {
        Scanner in = new Scanner(System.in);
        PrintStream printStream = new PrintStream(System.out);
        String newName, newX, newY, newEnginePower, newDistanceTravelled, newType, newFuelType;
        ArrayList<String> newValues = new ArrayList<>();
        String newValue = "";
        for (Process process : ValueHandler.getValueProcesses()) {
            do {
                Console.printUserErrorsFile();
                FileHandler.clearUserErrFile();
                printStream.print(process.getMessage());
                newValue = in.nextLine().trim();
                newValue = process.getCorrection().correct(newValue);
            } while (!process.getChecker().check(newValue));
            newValues.add(newValue);
        }
        newName = newValues.get(0);
        newX = newValues.get(1);
        newY = newValues.get(2);
        newEnginePower = newValues.get(3);
        newDistanceTravelled = newValues.get(4);
        newType = newValues.get(5);
        newFuelType = newValues.get(6);
        return ValueTransformer.createVehicle(id, newName, newX, newY, creationDate,
                newEnginePower, newDistanceTravelled, newType, newFuelType);
    }

    public static void printHelpMessage() {
        PrintStream printStream = new PrintStream(System.out);
        printStream.println("Type 'help' and press Enter to " +
                "see a list of commands");
    }

    public static String getHelpMessage() {
        return "Type 'help' and press Enter to see a list of commands";
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
        print(ANSI_GREEN + FileHandler.readOutFile() + ANSI_RESET);
    }

    public static void printUserErrorsFile() {
        print(ANSI_RED + FileHandler.readUserErrFile() + ANSI_RESET);
    }
}
