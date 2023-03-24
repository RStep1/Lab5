package processing;


import java.util.Arrays;

public class CommandParser {
    private CommandInvoker invoker;

    public CommandParser(CommandInvoker invoker) {
        this.invoker = invoker;
    }

    private boolean commandSelection(String nextLine, String nextCommand, String[] arguments, int splitedLineLength, ExecuteMode executeMode) {
        boolean exitStatus;
        switch (nextCommand) {
            case "help" -> {
                exitStatus = invoker.help(arguments, executeMode);
            }
            case "info" -> exitStatus = invoker.info(arguments, executeMode);
            case "show" -> exitStatus = invoker.show(arguments, executeMode);
            case "insert" -> exitStatus = invoker.insert(arguments, executeMode);
            case "update" -> exitStatus = invoker.update(arguments, executeMode);
            case "remove_key" -> exitStatus = invoker.removeKey(arguments, executeMode);
            case "clear" -> exitStatus = invoker.clear(arguments, executeMode);
            case "save" -> exitStatus = invoker.save(arguments, executeMode);
            case "execute_script" -> exitStatus = invoker.executeScript(arguments, ExecuteMode.SCRIPT_MODE);
            case "exit" -> exitStatus = invoker.exit(arguments, executeMode);
            case "remove_greater" -> exitStatus = invoker.removeGreater(arguments, executeMode);
            case "remove_lower" -> exitStatus = invoker.removeLower(arguments, executeMode);
            case "remove_greater_key" -> exitStatus = invoker.removeGreaterKey(arguments, executeMode);
            case "remove_all_by_engine_power" -> exitStatus = invoker.removeAllByEnginePower(arguments, executeMode);
            case "count_by_fuel_type" -> exitStatus = invoker.countByFuelType(arguments, executeMode);
            case "filter_less_than_fuel_type" -> exitStatus = invoker.filterLessThanFuelType(arguments, executeMode);
            default -> {
                FileHandler.writeUserErrors(String.format("'%s': No such command", nextLine.trim()));
                exitStatus = false;
            }
        }
        return exitStatus;
    }

    public void commandProcessing(String nextLine, ExecuteMode executeMode) {
        if (nextLine.trim().equals(""))
            return;
        String nextSplitedLine[] = nextLine.trim().split("\\s+");
        String[] arguments = new String[nextSplitedLine.length - 1];
        for (int i = 1; i < nextSplitedLine.length; i++) {
            arguments[i - 1] = nextSplitedLine[i];
        }
        String nextCommand = nextSplitedLine[0];
        boolean exitStatus = commandSelection(nextLine, nextCommand, arguments, nextSplitedLine.length - 1, executeMode);
        Console.printOutputFile();
        if (!exitStatus) {
            FileHandler.writeUserErrors(Console.getHelpMessage());
            Console.printUserErrorsFile();
        }
        FileHandler.clearOutFile();
        FileHandler.clearUserErrFile();
    }
}
