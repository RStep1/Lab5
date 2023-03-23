package processing;

import data.Vehicle;
import exceptions.NoSuchIdException;
import exceptions.WrongAmountOfArgumentsException;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import commands.*;

public class BufferedDataBase {
    private Hashtable<Long, Vehicle> dataBase;
    private LocalDateTime lastInitTime;
    private LocalDateTime lastSaveTime;
    private static final String datePattern = "dd/MM/yyy - HH:mm:ss";
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(datePattern);

    public BufferedDataBase() {
        dataBase = FileHandler.loadDataBase();
        lastInitTime = dataBase.isEmpty() && lastInitTime == null ? null : LocalDateTime.now();
    }

    public Hashtable<Long, Vehicle> getDataBase() {
        return dataBase;
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
        String stringLastInitTime = (lastInitTime == null ?
                "there have been no initializations in this session yet" : lastInitTime.format(dateFormatter));
        String stringLastSaveTime = (lastSaveTime == null ?
                "there hasn't been a save here yet" : lastSaveTime.format(dateFormatter));
        System.out.println(stringLastInitTime);
        FileHandler.writeCurrentCommand(InfoCommand.getName());
        FileHandler.writeOutputInfo("Information about collection:");
        FileHandler.writeOutputInfo("Type of collection:  " + getCollectionType() +
                                  "\nInitialization date: " + stringLastInitTime +
                                  "\nLast save time:      " + stringLastSaveTime +
                                  "\nNumber of elements:  " + getCollectionSize());
        return true;
    }

    public boolean show(String[] arguments, ExecuteMode executeMode) {
        if (!checkNumberOfArguments(arguments, 0, ShowCommand.getName()))
            return false;
        FileHandler.writeCurrentCommand(ShowCommand.getName());
        if (dataBase.isEmpty()) {
           FileHandler.writeOutputInfo("Collection is empty");
           return true;
        }
        TreeMap<Long, Vehicle> treeMapData = new TreeMap<>(dataBase);
        Set<Long> keys = treeMapData.keySet();
        Iterator<Long> iterator = keys.iterator();
        while (iterator.hasNext()) {
            Long key = iterator.next();
            FileHandler.writeOutputInfo("key:                " + key +
                    "\n" + treeMapData.get(key) + "");
        }
        return true;
    }

    public boolean insert(String[] arguments, ExecuteMode executeMode) {
        return addElementBy(arguments, executeMode, AddMode.INSERT_MODE, InsertCommand.getName());
    }

    public boolean update(String[] arguments, ExecuteMode executeMode) {
        return addElementBy(arguments, executeMode, AddMode.UPDATE_MODE, UpdateCommand.getName());
    }

    private boolean addElementBy(String[] arguments, ExecuteMode executeMode, AddMode addMode, String commandName) {
        if (arguments.length == 0) {
            FileHandler.writeUserErrors(String.format("%s value cannot be null", addMode.getValueName()));
            return false;
        }
        if (!checkNumberOfArguments(arguments, 1, commandName))
            return false;
        CollectionHandler collectionHandler = new CollectionHandler(dataBase, executeMode);
        java.time.ZonedDateTime creationDate = ZonedDateTime.now();
        long key = 0;
        long id = 0;
        switch (addMode) {
            case INSERT_MODE -> {
                if (!collectionHandler.checkKey(arguments[0]))
                    return false;
                key = Long.parseLong(arguments[0]);
                id = collectionHandler.generateId();
            }
            case UPDATE_MODE -> {
                if (!collectionHandler.checkId(arguments[0]))
                    return false;
                id = Long.parseLong(arguments[0]);
                key = getKeyById(id);
            }
            default -> FileHandler.writeSystemErrors("No suitable add mode file");
        }
        Vehicle vehicle = Console.insertMode(id, creationDate, collectionHandler);
        dataBase.put(key, vehicle);
        FileHandler.writeCurrentCommand(commandName);
        FileHandler.writeOutputInfo("Element was successfully " + addMode.getResultMessage());
        return true;
    }

    private long getKeyById(long id) {
        long key = -1;
        Enumeration<Long> keys = dataBase.keys();
        while (keys.hasMoreElements()) {
            Long nextKey = keys.nextElement();
            if (id == dataBase.get(nextKey).getId()) {
                key = nextKey;
            }
        }
        if (key == -1) {
            RuntimeException e = new NoSuchIdException(id);
            FileHandler.writeSystemErrors(e.getMessage());
            throw e;
        }
        return key;
    }

    public boolean removeKey(String[] arguments, ExecuteMode executeMode) {

        return true;
    }

    public boolean clear(String[] arguments, ExecuteMode executeMode) {
        if (!checkNumberOfArguments(arguments, 0, ClearCommand.getName()))
            return false;
        FileHandler.writeCurrentCommand(ClearCommand.getName());
        if (dataBase.isEmpty()) {
            FileHandler.writeOutputInfo("Collection is already empty");
        } else {
            dataBase.clear();
            FileHandler.writeOutputInfo("Collection successfully cleared");
        }
        return true;
    }

    public boolean save(String[] arguments, ExecuteMode executeMode) {
        if (!checkNumberOfArguments(arguments, 0, SaveCommand.getName()))
            return false;
        FileHandler.writeCurrentCommand(SaveCommand.getName());
        FileHandler.saveDataBase(dataBase);
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
        return dataBase.getClass().getName();
    }

    public int getCollectionSize() {
        return dataBase.size();
    }
}
