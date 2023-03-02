package processing;



public class CommandParser {
    private CommandInvoker invoker;

    public CommandParser(CommandInvoker invoker) {
        this.invoker = invoker;
    }


    public void commandProcessing(String nextLine) {
        String nextSplitedLine[] = nextLine.trim().split("\\s+");
        String[] arguments = new String[nextSplitedLine.length];

        for (int i = 1; i < nextSplitedLine.length; i++) {
            arguments[i] = nextSplitedLine[i];
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
            //...

            //хранить отдельно название и дескрипторы команд
        }
    }

    public void unconditionalCommandProcessing(String nextLine) {

    }
}
