package processing;


import commands.ExitCommand;
import commands.InsertCommand;
import commands.UpdateCommand;
import data.Vehicle;
import mods.AddMode;
import mods.ExecuteMode;
import mods.FileType;
import org.codehaus.jackson.JsonToken;
import utility.FileHandler;

import java.util.ArrayList;
import java.util.Objects;

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

    private boolean commandSelection(String nextLine, String nextCommand, String[] arguments,
                                     String[] vehicleValues, ExecuteMode executeMode) {
        boolean exitStatus;
        switch (nextCommand) {
            case "help" -> exitStatus = invoker.help(arguments, vehicleValues, executeMode);
            case "info" -> exitStatus = invoker.info(arguments, vehicleValues, executeMode);
            case "show" -> exitStatus = invoker.show(arguments, vehicleValues, executeMode);
            case "insert" -> exitStatus = invoker.insert(arguments, vehicleValues, executeMode);
            case "update" -> exitStatus = invoker.update(arguments, vehicleValues, executeMode);
            case "remove_key" -> exitStatus = invoker.removeKey(arguments, vehicleValues, executeMode);
            case "clear" -> exitStatus = invoker.clear(arguments, vehicleValues, executeMode);
            case "save" -> exitStatus = invoker.save(arguments, vehicleValues, executeMode);
            case "execute_script" -> exitStatus = invoker.executeScript(arguments, vehicleValues, executeMode);
            case "exit" -> exitStatus = invoker.exit(arguments, vehicleValues, executeMode);
            case "remove_greater" -> exitStatus = invoker.removeGreater(arguments, vehicleValues, executeMode);
            case "remove_lower" -> exitStatus = invoker.removeLower(arguments, vehicleValues, executeMode);
            case "remove_greater_key" -> exitStatus = invoker.removeGreaterKey(arguments, vehicleValues, executeMode);
            case "remove_all_by_engine_power" -> exitStatus =
                    invoker.removeAllByEnginePower(arguments, vehicleValues, executeMode);
            case "count_by_fuel_type" -> exitStatus = invoker.countByFuelType(arguments, vehicleValues, executeMode);
            case "filter_less_than_fuel_type" -> exitStatus =
                    invoker.filterLessThanFuelType(arguments, vehicleValues, executeMode);
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
        String[] extraArguments = new String[0];
        boolean exitStatus = commandSelection(nextLine, nextCommand, arguments, extraArguments, executeMode);
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
            if (nextCommand.equals(InsertCommand.getName()) || nextCommand.equals(UpdateCommand.getName()))
                countOfExtraArguments = Vehicle.getCountOfChangeableFields();
            String[] extraArguments = setExtraArguments(countOfExtraArguments, lineIndex, countOfLines);
            boolean exitStatus = commandSelection(nextLine, nextCommand, arguments,
                    extraArguments, ExecuteMode.SCRIPT_MODE);
            lineIndex += extraArguments.length;
            if (exitStatus && nextCommand.equals(ExitCommand.getName())) {
                FileHandler.writeCurrentCommand(ExitCommand.getName(), FileType.OUTPUT);
                FileHandler.writeToFile(String.format(
                        "Script '%s' successfully completed", scriptName), FileType.OUTPUT);
                return true;
            }
        }
        if (!hasCommands)
            FileHandler.writeToFile(String.format("Script '%s' is empty", scriptName), FileType.USER_ERRORS);
        return true;
    }

    private String[] setExtraArguments(int countOfExtraArguments, int lineIndex, int countOfLines) {
        String[] extraArguments = new String[Math.min(countOfExtraArguments, countOfLines - lineIndex - 1)];
        for (int i = 0, j = lineIndex + 1; j < lineIndex + countOfExtraArguments + 1 && j < countOfLines; j++, i++)
            extraArguments[i] = scriptLines.get(j).trim();
        return extraArguments;
    }
}
