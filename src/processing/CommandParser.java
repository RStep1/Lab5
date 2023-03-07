package processing;


public class CommandParser {
    private CommandInvoker invoker;

    public CommandParser(CommandInvoker invoker) {
        this.invoker = invoker;
    }

    private boolean commandSelection(String nextLine,
                                     String nextCommand,
                                     String[] arguments,
                                     int splitedLineLength) {
        boolean exitStatus;
        switch (nextCommand) {
            case "help" -> {
                arguments[splitedLineLength - 1] =
                        invoker.getCommandsDescription();
                exitStatus = invoker.help(arguments);
            }
            case "info" -> exitStatus = invoker.info(arguments);
            case "show" -> exitStatus = invoker.show(arguments);
            case "insert" -> exitStatus = invoker.insert(arguments);
            case "update" -> exitStatus = invoker.update(arguments);
            case "remove_key" -> exitStatus = invoker.removeKey(arguments);
            case "clear" -> exitStatus = invoker.clear(arguments);
            case "save" -> exitStatus = invoker.save(arguments);
            case "execute_script" -> exitStatus = invoker.executeScript(arguments);
            case "exit" -> exitStatus = invoker.exit(arguments);
            case "remove_greater" -> exitStatus = invoker.removeGreater(arguments);
            case "remove_lower" -> exitStatus = invoker.removeLower(arguments);
            case "remove_greater_key" -> exitStatus = invoker.removeGreaterKey(arguments);
            case "remove_all_by_engine_type" -> exitStatus = invoker.removeAllByEnginePower(arguments);
            case "count_by_fuel_type" -> exitStatus = invoker.countByFuelType(arguments);
            case "filter_less_than_fuel_type" -> exitStatus = invoker.filterLessThanFuelType(arguments);
            default -> {
                FileHandler.writeUserErrors(nextLine + ": No such command");
                exitStatus = false;
            }
        }
        return exitStatus;
    }

    public void commandProcessing(String nextLine) {
        String nextSplitedLine[] = nextLine.trim().split("\\s+");
        String[] arguments = new String[nextSplitedLine.length];
        for (int i = 1; i < nextSplitedLine.length; i++) {
            arguments[i - 1] = nextSplitedLine[i];
        }
        String nextCommand = nextSplitedLine[0];
        boolean exitStatus = commandSelection(nextLine, nextCommand,
                                            arguments,
                                            nextSplitedLine.length);
        if (exitStatus) {
            Console.printOutputFile();
        } else {
            FileHandler.writeUserErrors(Console.getHelpMessage());
            Console.printUserErrorsFile();
        }
        FileHandler.clearOutFile();
        FileHandler.clearUserErrFile();
    }
}
