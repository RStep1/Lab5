package processing;

import data.Vehicle;
import exceptions.WrongAmountOfArgumentsException;

import java.io.File;
import java.time.LocalDateTime;
import java.util.Hashtable;
import commands.*;

public class BufferedDataBase {
    private Hashtable<Integer, Vehicle> data = new Hashtable<>();
    private LocalDateTime lastInitTime;
    private LocalDateTime lastSaveTime;

    public BufferedDataBase() {
    }

    private boolean checkNumberOfArguments(String[] arguments, int expectedNumberOfArguments) {
        try {
            if (arguments.length != expectedNumberOfArguments + 1)
                throw new WrongAmountOfArgumentsException("Wrong amount of arguments: ",
                        arguments.length - 1, expectedNumberOfArguments);
            return true;
        } catch (WrongAmountOfArgumentsException e) {
            FileHandler.writeUserErrors(e.getMessage());
        }
        return false;
    }

    public boolean help(String[] arguments) {
        FileHandler.writeOutputInfo("Command " + HelpCommand.getName() + ":");
        if (!checkNumberOfArguments(arguments, 0)) {
            return false;
        }
        FileHandler.writeOutputInfo(arguments[0]);
        return true;
    }

    public boolean info(String[] arguments) {
        FileHandler.writeOutputInfo("Command " + InfoCommand.getName() + ":");
        if (!checkNumberOfArguments(arguments, 0)) {
            return false;
        }
        FileHandler.writeOutputInfo("Information about collection:");
        FileHandler.writeOutputInfo("Type of collection: " + getCollectionType() +
                "\nInitialization date: " + lastInitTime +
                "\nLast " + lastSaveTime +
                "\nNumber of elements: " + getCollectionSize());
        return true;
    }

    public boolean show(String[] arguments) {

        return true;
    }

    public boolean insert(String[] arguments) {

        return true;
    }

    public boolean update(String[] arguments) {

        return true;
    }

    public boolean removeKey(String[] arguments) {

        return true;
    }

    public boolean clear(String[] arguments) {
        FileHandler.writeOutputInfo("Command " + ClearCommand.getName() + ":");
        if (!checkNumberOfArguments(arguments, 0)) {
            return false;
        }
        if (getCollectionSize() == 0) {
            FileHandler.writeOutputInfo("Collection is already empty");
        } else {
            data.clear();
            FileHandler.writeOutputInfo("Collection successfully cleared");
        }
        return true;
    }

    public boolean save(String[] arguments) {

        return true;
    }

    public boolean executeScript(String[] arguments) {

        return true;
    }


    public boolean exit(String[] arguments) {
        FileHandler.writeOutputInfo("Command " + ExitCommand.getName() + ":");
        if (!checkNumberOfArguments(arguments, 0)) {
            return false;
        }
        FileHandler.writeOutputInfo("Program completed");
        Console.printOutputFile();
        if (!FileHandler.readUserErrFile().isEmpty()) { // for scripts
            FileHandler.writeUserErrors(Console.getHelpMessage());
            Console.printUserErrorsFile();
        }
        FileHandler.clearOutFile();
        FileHandler.clearUserErrFile();
        System.exit(0);///////////////////
        return true;
    }

    public boolean removeGreater(String[] arguments) {

        return true;
    }

    public boolean removeLower(String[] arguments) {

        return true;
    }

    public boolean removeGreaterKey(String[] arguments) {

        return true;
    }

    public boolean removeAllByEnginePower(String[] arguments) {

        return true;
    }

    public boolean countByFuelType(String[] arguments) {

        return true;
    }

    public boolean filterLessThanFuelType(String[] arguments) {

        return true;
    }

    public String getCollectionType() {
        return data.getClass().getName();
    }

    public int getCollectionSize() {
        return data.size();
    }


}
