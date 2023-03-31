package processing;

import data.Coordinates;
import data.FuelType;
import data.Vehicle;
import data.VehicleType;
import mods.ExecuteMode;
import util.ValueOperator;

import javax.sound.sampled.Line;
import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.BiFunction;
import java.util.function.Function;


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


    public static Vehicle insertMode(long id, java.time.ZonedDateTime creationDate, CollectionHandler collectionHandler) {
        Scanner in = new Scanner(System.in);
        PrintStream printStream = new PrintStream(System.out);
        String newName, newX, newY, newEnginePower, newDistanceTravelled, newType, newFuelType;
        do {
            if (collectionHandler.getExecuteMode() == ExecuteMode.COMMAND_MODE) {
                Console.printUserErrorsFile();
                FileHandler.clearUserErrFile();
            }
            printStream.print("Enter name: ");
            newName = in.nextLine().trim();
        } while (!collectionHandler.checkName(newName));

        do {
            if (collectionHandler.getExecuteMode() == ExecuteMode.COMMAND_MODE) {
                Console.printUserErrorsFile();
                FileHandler.clearUserErrFile();
            }
            printStream.print("Enter X coordinate: ");
            newX = in.nextLine().trim();
            newX = ValueOperator.VALUE_CORRECTION.apply(newX);
        } while (!collectionHandler.checkX(newX));

        do {
            if (collectionHandler.getExecuteMode() == ExecuteMode.COMMAND_MODE) {
                Console.printUserErrorsFile();
                FileHandler.clearUserErrFile();
            }
            printStream.print("Enter Y coordinate: ");
            newY = in.nextLine().trim();
            newY = ValueOperator.VALUE_CORRECTION.apply(newY);
        } while (!collectionHandler.checkY(newY));

        do {
            if (collectionHandler.getExecuteMode() == ExecuteMode.COMMAND_MODE) {
                Console.printUserErrorsFile();
                FileHandler.clearUserErrFile();
            }
            printStream.print("Enter engine power: ");
            newEnginePower = in.nextLine().trim();
            newEnginePower = ValueOperator.VALUE_CORRECTION.apply(newEnginePower);
        } while (!collectionHandler.checkEnginePower(newEnginePower));

        do {
            if (collectionHandler.getExecuteMode() == ExecuteMode.COMMAND_MODE) {
                Console.printUserErrorsFile();
                FileHandler.clearUserErrFile();
            }
            printStream.print("Enter distance travelled: ");
            newDistanceTravelled = in.nextLine().trim();
            newDistanceTravelled = ValueOperator.VALUE_CORRECTION.apply(newDistanceTravelled);
        } while (!collectionHandler.checkDistanceTravelled(newDistanceTravelled));

        do {
            if (collectionHandler.getExecuteMode() == ExecuteMode.COMMAND_MODE) {
                Console.printUserErrorsFile();
                FileHandler.clearUserErrFile();
            }
            printStream.println("Vehicle types:");
            for (VehicleType vehicleType : VehicleType.values()) {
                printStream.println(vehicleType.getSerialNumber() + " - " + vehicleType);
            }
            printStream.print("Enter vehicle type (numeric value or full name): ");
            newType = in.nextLine().trim().toUpperCase();
        } while (!collectionHandler.checkVehicleType(newType));

        do {
            if (collectionHandler.getExecuteMode() == ExecuteMode.COMMAND_MODE) {
                Console.printUserErrorsFile();
                FileHandler.clearUserErrFile();
            }
            printStream.println("fuel types:");
            for (FuelType fuelType : FuelType.values()) {
                printStream.println(fuelType.getSerialNumber() + " - " + fuelType);
            }
            printStream.print("Enter fuel type (numeric value or full name): ");
            newFuelType = in.nextLine().trim().toUpperCase();
        } while (!collectionHandler.checkFuelType(newFuelType));

        return ValueOperator.setVehicle(id, newName, newX, newY, creationDate,
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
