import processing.BufferedDataBase;
import processing.CommandInvoker;
import processing.Console;
import commands.*;

import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedDataBase bufferedDataBase = new BufferedDataBase();
        CommandInvoker invoker = new CommandInvoker(new HelpCommand(bufferedDataBase),
                new InfoCommand(bufferedDataBase), new ExitCommand(bufferedDataBase));

        Console console = new Console(invoker);
        console.interactiveMode();


    }
}