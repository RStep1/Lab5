package run;

import org.codehaus.jackson.map.util.ISO8601Utils;
import processing.*;
import commands.*;


import java.io.File;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.Hashtable;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedDataBase bufferedDataBase = new BufferedDataBase();
        CollectionHandler collectionHandler = new CollectionHandler(bufferedDataBase.getData(), ExecuteMode.COMMAND_MODE);
        CommandInvoker invoker = new CommandInvoker(new HelpCommand(bufferedDataBase),
                new InfoCommand(bufferedDataBase), new ShowCommand(bufferedDataBase),
                new InsertCommand(bufferedDataBase), new UpdateCommand(bufferedDataBase),
                new RemoveKeyCommand(bufferedDataBase), new ClearCommand(bufferedDataBase),
                new SaveCommand(bufferedDataBase), new ExecuteScriptCommand(bufferedDataBase),
                new ExitCommand(bufferedDataBase), new RemoveGreaterCommand(bufferedDataBase),
                new RemoveLowerCommand(bufferedDataBase),
                new RemoveGreaterKeyCommand(bufferedDataBase),
                new RemoveAllByEnginePowerCommand(bufferedDataBase),
                new CountByFuelTypeCommand(bufferedDataBase),
                new FilterLessThanFuelTypeCommand(bufferedDataBase));
//        System.out.println(collectionHandler.checkId("4"));
//        System.out.println(collectionHandler.checkId("3421456615"));
//        System.out.println(collectionHandler.checkId("fj544jl"));
//        System.out.println(collectionHandler.checkId("!@$^%*^5&^%"));
//        Console.printUserErrorsFile();
//        FileHandler.clearUserErrFile();

//        System.out.println(collectionHandler.checkId("3223576638"));
//        System.out.println(collectionHandler.checkId("445"));
//        System.out.println(collectionHandler.checkId("2345234523452345"));
//        System.out.println(collectionHandler.checkId("-23452"));
//        System.out.println(collectionHandler.checkId("~`"));
//        Console.printUserErrorsFile();
        Console console = new Console(invoker);
        console.interactiveMode();
    }
}