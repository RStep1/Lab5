package processing;

import data.Vehicle;
import exceptions.WrongAmountOfArgumentsException;

import java.time.LocalDateTime;
import java.util.Hashtable;
import commands.*;

public class BufferedDataBase {
    private Hashtable<Integer, Vehicle> data = new Hashtable<>();
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
            //Console.println(e.getMessage());
            FileHandler.writeUserErrors(e.getMessage());
            //Console.printHelpMessage();
            //сделать вывод help message в конце, если файл не пустой
        }
        return false;
    }

    public boolean help(String[] arguments) {
        if (!checkNumberOfArguments(arguments,
                HelpCommand.getName(), 0)) {
            return false;
        }
        //Console.println(arguments[0]);
        FileHandler.writeOutputInfo(arguments[0]);
        return true;
    }

    public boolean info(String[] arguments) {
        if (!checkNumberOfArguments(arguments,
                InfoCommand.getName(), 0)) {
            return false;
        }

        /*Console.println("Information about collection:");
        Console.println("Type of collection: " + getCollectionType() +
                "\nInitialization date: " + lastInitTime +
                "\nLast " + lastSaveTime +
                "\nNumber of elements: " + getCollectionSize());

         */
        FileHandler.writeOutputInfo("Command " + InfoCommand.getName() + ":");
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
        if (!checkNumberOfArguments(arguments,
                ClearCommand.getName(), 0)) {
            return false;
        }
        FileHandler.writeOutputInfo("Command " + ClearCommand.getName() + ":");
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
        if (!checkNumberOfArguments(arguments,
                ExitCommand.getName(), 0)) {
            return false;
        }
        FileHandler.writeOutputInfo("Command " + ExitCommand.getName() +
                ":");
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
