package processing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
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
        PrintStream printStream = new PrintStream(System.out);
        printStream.println("Print 'help' and press Enter to " +
                "see a list of commands");
    }

    public static void println(String message) {
        PrintStream printStream = new PrintStream(System.out);
        printStream.println(message);
    }
}
