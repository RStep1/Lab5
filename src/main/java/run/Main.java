package run;

import data.VehicleType;
import org.codehaus.jackson.map.util.ISO8601Utils;
import processing.*;
import commands.*;


import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.ZonedDateTime;
import java.util.Hashtable;
import java.util.Scanner;


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

//        System.out.println(Math.getExponent(0.02));
//        System.out.println("double:");
//        System.out.println(Double.MAX_VALUE);
//        System.out.println(Double.MAX_EXPONENT);
//
//        System.out.println("float:");
//        System.out.println(Float.MAX_VALUE);
//        System.out.println(Float.MAX_EXPONENT);
//
//        System.out.println("int:");
//        System.out.println(Integer.MAX_VALUE);
//        System.out.println(Integer.MIN_VALUE);
//
//        System.out.println("long:");
//        System.out.println(Long.MAX_VALUE);
//        System.out.println(Long.MIN_VALUE);
//        Scanner scanner = new Scanner(System.in);
//        String sd = scanner.nextLine();
//        double d = Double.parseDouble(sd);
//        System.out.println(d);


//        Double x = Double.parseDouble("345.000001");
//        Double truncatedDouble = BigDecimal.valueOf(x).setScale(6, RoundingMode.HALF_UP).doubleValue();
//
//        System.out.println(truncatedDouble);
//304985675693456345096730945693945967937
//29803672037569820000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000
        
        Console console = new Console(invoker);
        console.interactiveMode();
    }
}