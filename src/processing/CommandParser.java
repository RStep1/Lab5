package processing;

import commands.*;


public class CommandParser {
    private CommandInvoker invoker;

    public CommandParser(CommandInvoker invoker) {
        this.invoker = invoker;
    }

    public void commandProcessing(String nextLine) {
        String nextSplitedLine[] = nextLine.trim().split("\\s+");
        String[] arguments = new String[nextSplitedLine.length];
        for (int i = 1; i < nextSplitedLine.length; i++) {
            arguments[i - 1] = nextSplitedLine[i];
        }
        String nextCommand = nextSplitedLine[0];

        switch (nextCommand) {
            case "help" -> {
                arguments[nextSplitedLine.length - 1] =
                        invoker.getCommandsDescription();
                invoker.help(arguments);
            }
            case "info" -> invoker.info(arguments);
            case "exit" -> invoker.exit(arguments);
            //case "show" -> invoker.show(arguments);
            //case "insert" -> invoker.insert(arguments);
            //case "update" -> invoker.update(arguments);
            case "clear" -> invoker.clear(arguments);
            default -> {
                Console.println(nextLine + ": No such command");
                Console.printHelpMessage();
            }
        }
    }

    public void unconditionalCommandProcessing(String nextLine) {

    }
}
