package processing;

import data.Vehicle;
import exceptions.WrongAmountOfArgumentsException;

import java.io.File;
import java.time.LocalDateTime;
import java.util.Enumeration;
import java.util.Hashtable;
import commands.*;

public class BufferedDataBase {
    private Hashtable<Long, Vehicle> data;
    private LocalDateTime lastInitTime;
    private LocalDateTime lastSaveTime;
    private FileHandler fileHandler;

    public BufferedDataBase() {
        data = FileHandler.loadDataBase();
        lastInitTime = data.isEmpty() && lastInitTime == null ? null : LocalDateTime.now();
    }

    private boolean checkNumberOfArguments(String[] arguments, int expectedNumberOfArguments, String commandName) {
        try {
            if (arguments.length != expectedNumberOfArguments) {
                FileHandler.writeCurrentCommand(commandName);
                throw new WrongAmountOfArgumentsException("Wrong amount of arguments: ",
                        arguments.length, expectedNumberOfArguments);
            }
            return true;
        } catch (WrongAmountOfArgumentsException e) {
            FileHandler.writeUserErrors(e.getMessage());
        }
        return false;
    }

    public boolean help(String[] arguments) {
        if (!checkNumberOfArguments(arguments, 0, HelpCommand.getName()))
            return false;
        FileHandler.writeCurrentCommand(HelpCommand.getName());
        FileHandler.writeOutputInfo(FileHandler.readReferenceFile());
        return true;
    }

    public boolean info(String[] arguments) {
        if (!checkNumberOfArguments(arguments, 0, InfoCommand.getName()))
            return false;
        FileHandler.writeCurrentCommand(InfoCommand.getName());
        FileHandler.writeOutputInfo("Information about collection:");
        FileHandler.writeOutputInfo("Type of collection: " + getCollectionType() +
                "\nInitialization date: " + lastInitTime +
                "\nLast save time: " + lastSaveTime +
                "\nNumber of elements: " + getCollectionSize());
        return true;
    }

    public boolean show(String[] arguments) {
        if (!checkNumberOfArguments(arguments, 0, ShowCommand.getName()))
            return false;
        FileHandler.writeCurrentCommand(ShowCommand.getName());
        if (data.isEmpty()) {
           FileHandler.writeOutputInfo("Collection is empty");
           return true;
        }
        Enumeration<Long> keys = data.keys();
        while (keys.hasMoreElements()) {
            Long key = keys.nextElement();
            FileHandler.writeOutputInfo(data.get(key) + "");
        }
        return true;
    }

    public boolean insert(String[] arguments) {
        CollectionHandler collectionHandler = new CollectionHandler(data);
        if (arguments.length == 0) {

        }
        if (!checkNumberOfArguments(arguments, 1, InfoCommand.getName()))
            return false;

//        Long key = Long.getLong(arguments[0]);
//        Console.insertMode(key);
        FileHandler.writeCurrentCommand(InsertCommand.getName());
        FileHandler.writeOutputInfo("Element was successfully added");
        return true;
    }

    public boolean update(String[] arguments) {

        return true;
    }

    public boolean removeKey(String[] arguments) {

        return true;
    }

    public boolean clear(String[] arguments) {
        if (!checkNumberOfArguments(arguments, 0, ClearCommand.getName()))
            return false;
        FileHandler.writeCurrentCommand(ClearCommand.getName());
        if (data.isEmpty()) {
            FileHandler.writeOutputInfo("Collection is already empty");
        } else {
            data.clear();
            FileHandler.writeOutputInfo("Collection successfully cleared");
        }
        return true;
    }

    public boolean save(String[] arguments) {
        if (!checkNumberOfArguments(arguments, 0, SaveCommand.getName()))
            return false;
        FileHandler.writeCurrentCommand(ClearCommand.getName());
        FileHandler.saveDataBase(data);
        FileHandler.writeOutputInfo("Collection successfully saved");
        lastSaveTime = LocalDateTime.now();
        return true;
    }

    public boolean executeScript(String[] arguments) {

        return true;
    }


    public boolean exit(String[] arguments) {
        if (!checkNumberOfArguments(arguments, 0, ExitCommand.getName()))
            return false;
        FileHandler.writeCurrentCommand(ExitCommand.getName());
        FileHandler.writeOutputInfo("Program successfully completed");
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
