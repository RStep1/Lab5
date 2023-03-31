package processing;


import commands.Command;
import commands.ExitCommand;
import commands.InsertCommand;
import commands.UpdateCommand;
import data.Vehicle;
import mods.ExecuteMode;

import java.util.ArrayList;

public class CommandParser {
    private CommandInvoker invoker;
    private ArrayList<String> scriptLines;

    public CommandParser(CommandInvoker invoker) {
        this.invoker = invoker;
    }

    public CommandParser(CommandInvoker invoker, ArrayList<String> scriptLines) {
        this.invoker = invoker;
        this.scriptLines = scriptLines;
    }

    private boolean commandSelection(String nextLine, String nextCommand, String[] arguments, ExecuteMode executeMode) {
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
            case "execute_script" -> exitStatus = invoker.executeScript(arguments, executeMode);
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

    private static class UserLineSeparator {
        private final String command;
        private final String[] arguments;
        public UserLineSeparator(String nextLine) {
            String[] nextSplitedLine = nextLine.trim().split("\\s+");
            this.arguments = new String[nextSplitedLine.length - 1];
            for (int i = 1; i < nextSplitedLine.length; i++) {
                this.arguments[i - 1] = nextSplitedLine[i];
            }
            this.command = nextSplitedLine[0];
        }
        public String getCommand() {
            return command;
        }
        public String[] getArguments() {
            return arguments;
        }
    }

    public boolean commandProcessing(String nextLine, ExecuteMode executeMode) {
        if (nextLine.trim().equals(""))
            return true;
        UserLineSeparator userLineSeparator = new UserLineSeparator(nextLine);
        String nextCommand = userLineSeparator.getCommand();
        String[] arguments = userLineSeparator.getArguments();
        boolean exitStatus = commandSelection(nextLine, nextCommand, arguments, executeMode);
        Console.printOutputFile();
        if (!exitStatus) {
            FileHandler.writeUserErrors(Console.getHelpMessage());
            Console.printUserErrorsFile();
        }
        FileHandler.clearOutFile();
        FileHandler.clearUserErrFile();

        if (nextCommand.equals(ExitCommand.getName()) && exitStatus)
            return false;
        return true;
    }

//    private String[] addExtraArguments(String[] arguments, int countOfLines,
//                                       String commandName, ArrayList<String> commandLines, int currentLine) {
//        int countOfExtraArguments = 0;
////        for (Command command : invoker.getCommandList()) {
////            if (command.getName() == "commandName")
////                countOfExtraArguments = command.getCountOfExtraArguments
////        }
//        if (commandName == InsertCommand.getName() || commandName == UpdateCommand.getName())
//            countOfExtraArguments = 7;
//        String[] extraArguments = new String[arguments.length + commandLines.size()];
//        for (int i = 0; i < arguments.length; i++) {
//            extraArguments[i] = arguments[i];
//        }
//        for (int i = arguments.length; i < extraArguments.length; i++) {
//
//        }
//
//    }

    public boolean scriptProcessing(String scriptName) {
        int countOfLines = scriptLines.size();
        for (int lineIndex = 0; lineIndex < countOfLines; lineIndex++) {
            String nextLine = scriptLines.get(lineIndex);
            if (nextLine.trim().equals(""))
                continue;
            UserLineSeparator userLineSeparator = new UserLineSeparator(nextLine);
            String nextCommand = userLineSeparator.getCommand();
            String[] arguments = userLineSeparator.getArguments();
            int countOfExtraArguments = 0;
            if (nextCommand.equals(InsertCommand.getName()) || nextCommand.equals(UpdateCommand.getName())) {
                countOfExtraArguments = Vehicle.getCountOfChangeableFields();
            }
            String[] extraArguments = new String[arguments.length + countOfExtraArguments];
            if (lineIndex + countOfExtraArguments > countOfLines) {
                FileHandler.writeUserErrors(String.format(
                        "line %s: There are not enough lines in script '%s' for the '%s' command",
                        lineIndex + 1, scriptName, nextCommand));
                return false;
            }
            for (int i = 0; i < arguments.length; i++)
                extraArguments[i] = arguments[i].trim();
            for (int i = arguments.length, j = lineIndex + 1; j < lineIndex + countOfExtraArguments + 1; j++, i++)
                extraArguments[i] = scriptLines.get(j).trim();
            lineIndex += countOfExtraArguments;
            boolean exitStatus = commandSelection(nextLine, nextCommand, extraArguments, ExecuteMode.SCRIPT_MODE);
            if (exitStatus && nextCommand.equals(ExitCommand.getName()))
                return true;
        }
        return true;
    }
}
