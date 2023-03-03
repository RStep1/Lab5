package processing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLOutput;
import java.util.Scanner;


public class Console {
    private CommandInvoker invoker;

    public Console(CommandInvoker invoker) {
        this.invoker = invoker;
    }

    public void interactiveMode() {
        //BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        CommandParser parser = new CommandParser(invoker);
        Scanner in = new Scanner(System.in);

        while (true) {
            String nextLine = in.nextLine();
            parser.commandProcessing(nextLine);

        }
    }

    public static void printHelpMessage() {
        System.out.println("Print 'help' and press Enter to " +
                "see a list of commands");
    }

    public static void println(String message) {
        System.out.println(message);
    }
}
