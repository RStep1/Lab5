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
            case "show" -> invoker.show(arguments);
            case "insert" -> invoker.insert(arguments);
            case "update" -> invoker.update(arguments);
            case "remove_key" -> invoker.removeKey(arguments);
            case "clear" -> invoker.clear(arguments);
            case "save" -> invoker.save(arguments);
            case "execute_script" -> invoker.executeScript(arguments);
            case "exit" -> invoker.exit(arguments);
            case "remove_greater" -> invoker.removeGreater(arguments);
            case "remove_lower" -> invoker.removeLower(arguments);
            case "remove_greater_key" -> invoker.removeGreaterKey(arguments);
            case "remove_all_by_engine_type" -> invoker.removeAllByEnginePower(arguments);
            case "count_by_fuel_type" -> invoker.countByFuelType(arguments);
            case "filter_less_than_fuel_type" -> invoker.filterLessThanFuelType(arguments);
            default -> {
                Console.println(nextLine + ": No such command");
                Console.printHelpMessage();
            }
        }
    }

    public void unconditionalCommandProcessing(String nextLine) {

    }
}
