package processing;

import data.Vehicle;
import exceptions.WrongAmountOfArgumentsException;

import java.io.File;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Hashtable;
import commands.*;

public class BufferedDataBase {
    private Hashtable<Long, Vehicle> data;
    private LocalDateTime lastInitTime;
    private LocalDateTime lastSaveTime;

    public BufferedDataBase() {
        data = FileHandler.loadDataBase();
        lastInitTime = data.isEmpty() && lastInitTime == null ? null : LocalDateTime.now();
    }

    public Hashtable<Long, Vehicle> getData() {
        return data;
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

    public boolean help(String[] arguments, ExecuteMode executeMode) {
        if (!checkNumberOfArguments(arguments, 0, HelpCommand.getName()))
            return false;
        FileHandler.writeCurrentCommand(HelpCommand.getName());
        FileHandler.writeOutputInfo(FileHandler.readReferenceFile());
        return true;
    }

    public boolean info(String[] arguments, ExecuteMode executeMode) {
        if (!checkNumberOfArguments(arguments, 0, InfoCommand.getName()))
            return false;
        FileHandler.writeCurrentCommand(InfoCommand.getName());
        FileHandler.writeOutputInfo("Information about collection:");
        FileHandler.writeOutputInfo("Type of collection:  " + getCollectionType() +
                                  "\nInitialization date: " + lastInitTime +
                                  "\nLast save time:      " + lastSaveTime +
                                  "\nNumber of elements:  " + getCollectionSize());
        return true;
    }

    public boolean show(String[] arguments, ExecuteMode executeMode) {
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
            FileHandler.writeOutputInfo("key:                " + key +
                    "\n" + data.get(key) + "");
        }
        return true;
    }

    public boolean insert(String[] arguments, ExecuteMode executeMode) {
        if (arguments.length == 0) {
            FileHandler.writeUserErrors("Key value cannot be null");
            return false;
        }
        if (!checkNumberOfArguments(arguments, 1, InsertCommand.getName())) {
            return false;
        }
        CollectionHandler collectionHandler = new CollectionHandler(data, executeMode);
        if (!collectionHandler.checkKey(arguments[0])) {
            return false;
        }
        Long key = Long.parseLong(arguments[0]);
        long id = collectionHandler.generateId();
        java.time.ZonedDateTime creationDate = ZonedDateTime.now();
        Vehicle vehicle = Console.insertMode(id, creationDate, collectionHandler);
        data.put(key, vehicle);
        FileHandler.writeCurrentCommand(InsertCommand.getName());
        FileHandler.writeOutputInfo("Element was successfully added");
        return true;
    }

    public boolean update(String[] arguments, ExecuteMode executeMode) {

        return true;
    }

    public boolean removeKey(String[] arguments, ExecuteMode executeMode) {

        return true;
    }

    public boolean clear(String[] arguments, ExecuteMode executeMode) {
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

    public boolean save(String[] arguments, ExecuteMode executeMode) {
        if (!checkNumberOfArguments(arguments, 0, SaveCommand.getName()))
            return false;
        FileHandler.writeCurrentCommand(SaveCommand.getName());
        FileHandler.saveDataBase(data);
        FileHandler.writeOutputInfo("Collection successfully saved");
        lastSaveTime = LocalDateTime.now();
        return true;
    }

    public boolean executeScript(String[] arguments, ExecuteMode executeMode) {

        return true;
    }


    public boolean exit(String[] arguments, ExecuteMode executeMode) {
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

    public boolean removeGreater(String[] arguments, ExecuteMode executeMode) {

        return true;
    }

    public boolean removeLower(String[] arguments, ExecuteMode executeMode) {

        return true;
    }

    public boolean removeGreaterKey(String[] arguments, ExecuteMode executeMode) {

        return true;
    }

    public boolean removeAllByEnginePower(String[] arguments, ExecuteMode executeMode) {

        return true;
    }

    public boolean countByFuelType(String[] arguments, ExecuteMode executeMode) {

        return true;
    }

    public boolean filterLessThanFuelType(String[] arguments, ExecuteMode executeMode) {

        return true;
    }

    public String getCollectionType() {
        return data.getClass().getName();
    }

    public int getCollectionSize() {
        return data.size();
    }


}
