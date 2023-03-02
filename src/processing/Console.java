package processing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;


public class Console {
    private CommandInvoker invoker;

    public Console(CommandInvoker invoker) {
        this.invoker = invoker;
    }

    public void interactiveMode() throws IOException {
        //BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        CommandParser parser = new CommandParser(invoker);
        Scanner in = new Scanner(System.in);

        while (true) {
            String nextLine = in.nextLine();
            parser.commandProcessing(nextLine);

        }
    }
}
