package processing;

import data.Vehicle;
import exceptions.WrongAmountOfArgumentsException;

import java.time.LocalDateTime;
import java.util.Hashtable;
import commands.*;

public class BufferedDataBase {
    private Hashtable<Integer, Vehicle> data = new Hashtable<Integer, Vehicle>();
    private LocalDateTime lastInitTime;
    private LocalDateTime lastSaveTime;

    public BufferedDataBase() {
    }

    private boolean checkNumberOfArguments(String[] arguments,
                                  String commandName,
                                  int expectedNumberOfArguments) {
        try {
            if (arguments.length != expectedNumberOfArguments + 1)
                throw new WrongAmountOfArgumentsException("Wrong amount of arguments: ",
                        commandName, arguments.length - 1, expectedNumberOfArguments);
            return true;
        } catch (WrongAmountOfArgumentsException e) {
            Console.println(e.getMessage());
            Console.printHelpMessage();
        }
        return false;
    }

    public boolean help(String[] arguments) {
        if (!checkNumberOfArguments(arguments,
                HelpCommand.getName(), 0)) {
            return false;
        }
        Console.println(arguments[0]);
        return true;
    }

    public boolean info(String[] arguments) {
        if (!checkNumberOfArguments(arguments,
                InfoCommand.getName(), 0)) {
            return false;
        }

        return true;
    }

    /*
    public boolean show(String argument) {

    }

    public boolean insert(String argument) {

    }

    public boolean update(String argument) {

    }

    public boolean removeKey(String argument) {

    }
    */

    public boolean clear(String[] arguments) {
        if (!checkNumberOfArguments(arguments,
                ClearCommand.getName(), 0)) {
            return false;
        }
        data.clear();
        return true;
    }
    /*
    public boolean save(String argument) {

    }

    public boolean executeScript(String argument) {

    }
    */

    public boolean exit(String[] arguments) {
        if (checkNumberOfArguments(arguments,
                ExitCommand.getName(), 0)) {
            System.exit(0);
            return true;
        }
        return false;
    }

    /*
    public boolean removeGreater(String argument) {

    }

    public boolean removeLower(String argument) {

    }

    public boolean removeGreaterKey(String argument) {

    }

    public boolean removeAllByEnginePower(String argument) {

    }

    public boolean countByFuelType(String argument) {

    }

    public boolean filterLessThanFuelType(String argument) {

    }

     */
    public String getCollectionType() {
        return data.getClass().getName();
    }

    public int getCollectionSize() {
        return data.size();
    }


}
