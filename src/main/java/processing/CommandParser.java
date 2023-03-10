package processing;


import java.util.Arrays;

public class CommandParser {
    private CommandInvoker invoker;

    public CommandParser(CommandInvoker invoker) {
        this.invoker = invoker;
    }

    private boolean commandSelection(String nextLine, String nextCommand, String[] arguments, int splitedLineLength) {
        boolean exitStatus;
        switch (nextCommand) {
            case "help" -> {
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
                FileHandler.writeUserErrors(nextLine.trim() + ": No such command");
                exitStatus = false;
            }
        }
        return exitStatus;
    }

    public void commandProcessing(String nextLine) {
        if (nextLine.trim() == "")
            return;
        String nextSplitedLine[] = nextLine.trim().split("\\s+");
        String[] arguments = new String[nextSplitedLine.length - 1];
        for (int i = 1; i < nextSplitedLine.length - 1; i++) {
            arguments[i - 1] = nextSplitedLine[i];
        }
        String nextCommand = nextSplitedLine[0];
        boolean exitStatus = commandSelection(nextLine, nextCommand, arguments, nextSplitedLine.length - 1);
        Console.printOutputFile();
        if (!exitStatus) {
            FileHandler.writeUserErrors(Console.getHelpMessage());
            Console.printUserErrorsFile();
        }
        FileHandler.clearOutFile();
        FileHandler.clearUserErrFile();
    }
}
