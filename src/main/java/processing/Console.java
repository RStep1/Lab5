package processing;

import data.Coordinates;
import data.FuelType;
import data.Vehicle;
import data.VehicleType;
import mods.ExecuteMode;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;


public class Console {
    private CommandInvoker invoker;
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String zonedDatePattern = "dd/MM/yyy - HH:mm:ss z";
    public static final DateTimeFormatter zonedDateFormatter = DateTimeFormatter.ofPattern(zonedDatePattern);

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
            parser.commandProcessing(nextLine, ExecuteMode.COMMAND_MODE);
        }
    }

    private static String valueCorrection(String value) {
        return value.replaceAll(",", ".").replaceAll("\\+", "");
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
            newX = valueCorrection(newX);
        } while (!collectionHandler.checkX(newX));

        do {
            if (collectionHandler.getExecuteMode() == ExecuteMode.COMMAND_MODE) {
                Console.printUserErrorsFile();
                FileHandler.clearUserErrFile();
            }
            printStream.print("Enter Y coordinate: ");
            newY = in.nextLine().trim();
            newY = valueCorrection(newY);
        } while (!collectionHandler.checkY(newY));

        do {
            if (collectionHandler.getExecuteMode() == ExecuteMode.COMMAND_MODE) {
                Console.printUserErrorsFile();
                FileHandler.clearUserErrFile();
            }
            printStream.print("Enter engine power: ");
            newEnginePower = in.nextLine().trim();
            newEnginePower = valueCorrection(newEnginePower);
        } while (!collectionHandler.checkEnginePower(newEnginePower));

        do {
            if (collectionHandler.getExecuteMode() == ExecuteMode.COMMAND_MODE) {
                Console.printUserErrorsFile();
                FileHandler.clearUserErrFile();
            }
            printStream.print("Enter distance travelled: ");
            newDistanceTravelled = in.nextLine().trim();
            newDistanceTravelled = valueCorrection(newDistanceTravelled);
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


        float x = Float.parseFloat(newX);
        double y = Double.parseDouble(newY);
        float truncatedX = BigDecimal.valueOf(x).setScale(Coordinates.getAccuracy(),
                RoundingMode.HALF_UP).floatValue();
        double truncatedY = BigDecimal.valueOf(y).setScale(Coordinates.getAccuracy(),
                RoundingMode.HALF_UP).doubleValue();
        Coordinates coordinates = new Coordinates(truncatedX, truncatedY);

        int enginePower = Integer.parseInt(newEnginePower);
        long distanceTravelled = Long.parseLong(newDistanceTravelled);
        VehicleType type = VehicleType.CAR;
        FuelType fuelType = FuelType.ALCOHOL;
        try {
            int serialNumber = Integer.parseInt(newType);
            for (VehicleType vehicleType : VehicleType.values())
                if (vehicleType.getSerialNumber() == serialNumber)
                    type = vehicleType;
        } catch (NumberFormatException e) {
            type = VehicleType.valueOf(newType);
        }
        try {
            int serialNumber = Integer.parseInt(newFuelType);
            for (FuelType fuelType1 : FuelType.values())
                if (fuelType1.getSerialNumber() == serialNumber)
                    fuelType = fuelType1;
        } catch (NumberFormatException e) {
            fuelType = FuelType.valueOf(newFuelType);
        }
        String stringCreationDate = creationDate.format(zonedDateFormatter);
        Vehicle vehicle = new Vehicle(id, newName, coordinates, stringCreationDate,
                enginePower, distanceTravelled, type, fuelType);
        return vehicle;
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
