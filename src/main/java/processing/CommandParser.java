package processing;


import commands.ExitCommand;
import commands.InsertCommand;
import commands.UpdateCommand;
import data.Vehicle;
import mods.ExecuteMode;
import mods.FileType;
import org.codehaus.jackson.JsonToken;
import utility.FileHandler;

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
                FileHandler.writeToFile(String.format("'%s': No such command", nextLine.trim()), FileType.USER_ERRORS);
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
        if (!FileHandler.readFile(FileType.USER_ERRORS).isEmpty()) {
            FileHandler.writeToFile(Console.getHelpMessage(), FileType.USER_ERRORS);
            Console.printUserErrorsFile();
        }
        FileHandler.clearFile(FileType.OUTPUT);
        FileHandler.clearFile(FileType.USER_ERRORS);

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
        boolean hasCommands = false;
        for (int lineIndex = 0; lineIndex < countOfLines; lineIndex++) {
            String nextLine = scriptLines.get(lineIndex);
            if (nextLine.trim().equals(""))
                continue;
            hasCommands = true;
            UserLineSeparator userLineSeparator = new UserLineSeparator(nextLine);
            String nextCommand = userLineSeparator.getCommand();
            String[] arguments = userLineSeparator.getArguments();
            int countOfExtraArguments = 0;
            if (nextCommand.equals(InsertCommand.getName()) || nextCommand.equals(UpdateCommand.getName())) {
                countOfExtraArguments = Vehicle.getCountOfChangeableFields();
            }
            String[] extraArguments = new String[arguments.length + countOfExtraArguments];
            if (lineIndex + countOfExtraArguments > countOfLines) {
                FileHandler.writeToFile(String.format(
                        "line %s: There are not enough lines in script '%s' for the '%s' command",
                        lineIndex + 1, scriptName, nextCommand), FileType.USER_ERRORS);
                return false;
            }
            for (int i = 0; i < arguments.length; i++)
                extraArguments[i] = arguments[i].trim();
            for (int i = arguments.length, j = lineIndex + 1; j < lineIndex + countOfExtraArguments + 1; j++, i++)
                extraArguments[i] = scriptLines.get(j).trim();
            lineIndex += countOfExtraArguments;
//            FileHandler.writeOutputInfo(String.format("%s line %s: ", scriptName, lineIndex + 1));
            boolean exitStatus = commandSelection(nextLine, nextCommand, extraArguments, ExecuteMode.SCRIPT_MODE);
            if (exitStatus && nextCommand.equals(ExitCommand.getName())) {
                FileHandler.writeToFile(String.format(
                        "Script '%s' successfully completed", scriptName), FileType.OUTPUT);
                return true;
            }
        }
        if (!hasCommands)
            FileHandler.writeToFile(String.format("Script '%s' is empty", scriptName), FileType.USER_ERRORS);
        return true;
    }
}
