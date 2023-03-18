package processing;

import data.Coordinates;
import data.FuelType;
import data.Vehicle;
import data.VehicleType;

import java.io.*;
import java.util.Scanner;


public class Console {
    private CommandInvoker invoker;

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

    public static Vehicle insertMode(long id, java.time.ZonedDateTime creationDate, CollectionHandler collectionHandler) {
        Scanner in = new Scanner(System.in);
        PrintStream printStream = new PrintStream(System.out);
        String newName, newX, newY, newEnginePower, newDistanceTravelled, newType, newFuelType;
        do {
            printStream.print("Enter name: ");
            newName = in.nextLine().trim();
        } while (!collectionHandler.checkName(newName));

        do {
            printStream.print("Enter X coordinate: ");
            newX = in.nextLine().trim();
        } while (!collectionHandler.checkX(newX));

        do {
            printStream.print("Enter Y coordinate: ");
            newY = in.nextLine().trim();
        } while (!collectionHandler.checkY(newY));

        do {
            printStream.print("Enter engine power: ");
            newEnginePower = in.nextLine().trim();
        } while (!collectionHandler.checkEnginePower(newEnginePower));

        do {
            printStream.print("Enter distance travelled: ");
            newDistanceTravelled = in.nextLine().trim();
        } while (!collectionHandler.checkDistanceTravelled(newDistanceTravelled));

        do {
            printStream.println("vehicle types:");
            for (VehicleType vehicleType : VehicleType.values()) {
                printStream.println(vehicleType.getSerialNumber() + " - " + vehicleType);
            }
            printStream.print("Enter vehicle type: ");
            newType = in.nextLine().trim();
        } while (!collectionHandler.checkVehicleType(newType));

        do {
            printStream.println("fuel types:");
            for (FuelType fuelType : FuelType.values()) {
                printStream.println(fuelType.getSerialNumber() + " - " + fuelType);
            }
            printStream.print("Enter fuel type: ");
            newFuelType = in.nextLine().trim();
        } while (!collectionHandler.checkFuelType(newFuelType));

        Coordinates coordinates = new Coordinates(Float.parseFloat(newX), Double.parseDouble(newY));
        int enginePower = Integer.parseInt(newEnginePower);
        long distanceTravelled = Long.parseLong(newDistanceTravelled);
        VehicleType type = VehicleType.CAR;/////
        FuelType fuelType = FuelType.ALCOHOL;/////
        Vehicle vehicle = new Vehicle(id, newName, coordinates, creationDate.toString(),
                enginePower, distanceTravelled, type, fuelType);
        return vehicle;
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
        print(FileHandler.readOutFile());
    }

    public static void printUserErrorsFile() {
        print(FileHandler.readUserErrFile());
    }
}
