package processing;

import commands.Command;
import commands.HelpCommand;

import java.util.ArrayList;

public class CommandInvoker {
    private Command helpCommand;
    private Command infoCommand;
    private Command showCommand;
    private Command insertCommand;
    private Command updateCommand;
    private Command removeKeyCommand;
    private Command clearCommand;
    private Command saveCommand;
    private Command executeScriptCommand;
    private Command exitCommand;
    private Command removeGreaterCommand;
    private Command removeLowerCommand;
    private Command removeGreaterKeyCommand;
    private Command removeAllByEnginePowerCommand;
    private Command countByFuelTypeCommand;
    private Command filterLessThanFuelTypeCommand;

    private ArrayList<Command> commandList = new ArrayList<>();

    private Command lastCommand;

    public CommandInvoker(Command helpCommand, Command infoCommand, Command showCommand,
                          Command insertCommand, Command updateCommand, Command removeKeyCommand,
                          Command clearCommand, Command saveCommand, Command executeScriptCommand,
                          Command exitCommand, Command removeGreaterCommand, Command removeLowerCommand,
                          Command removeGreaterKeyCommand, Command removeAllByEnginePowerCommand,
                          Command countByFuelTypeCommand, Command filterLessThanFuelTypeCommand) {
        this.helpCommand = helpCommand;
        this.infoCommand = infoCommand;
        this.showCommand = showCommand;
        this.insertCommand = insertCommand;
        this.updateCommand = updateCommand;
        this.removeKeyCommand = removeKeyCommand;
        this.clearCommand = clearCommand;
        this.saveCommand = saveCommand;
        this.executeScriptCommand = executeScriptCommand;
        this.exitCommand = exitCommand;
        this.removeGreaterCommand = removeGreaterCommand;
        this.removeLowerCommand = removeLowerCommand;
        this.removeGreaterKeyCommand = removeGreaterKeyCommand;
        this.removeAllByEnginePowerCommand = removeAllByEnginePowerCommand;
        this.countByFuelTypeCommand = countByFuelTypeCommand;
        this.filterLessThanFuelTypeCommand = filterLessThanFuelTypeCommand;

        commandList.add(helpCommand);
        commandList.add(infoCommand);
        commandList.add(showCommand);
        commandList.add(insertCommand);
        commandList.add(updateCommand);
        commandList.add(removeKeyCommand);
        commandList.add(clearCommand);
        commandList.add(saveCommand);
        commandList.add(executeScriptCommand);
        commandList.add(exitCommand);
        commandList.add(removeGreaterCommand);
        commandList.add(removeLowerCommand);
        commandList.add(removeGreaterKeyCommand);
        commandList.add(removeAllByEnginePowerCommand);
        commandList.add(countByFuelTypeCommand);
        commandList.add(filterLessThanFuelTypeCommand);
    }

    public ArrayList<Command> getCommandList(){
        return commandList;
    }

    public String getCommandsDescription() {
        String reference = "";
        for (Command command : commandList) {
            reference += command + "\n";
        }
        return reference;
    }

    public boolean help(String[] arguments) {
        return helpCommand.execute(arguments);
    }

    public boolean info(String[] arguments) {
        return infoCommand.execute(arguments);
    }

    public boolean show(String[] arguments) {
        return showCommand.execute(arguments);
    }

    public boolean insert(String[] arguments) {
        return insertCommand.execute(arguments);
    }

    public boolean update(String[] arguments) {
        return updateCommand.execute(arguments);
    }

    public boolean removeKey(String[] arguments) {
        return removeKeyCommand.execute(arguments);
    }

    public boolean clear(String[] arguments) {
        return clearCommand.execute(arguments);
    }

    public boolean save(String[] arguments) {
        return saveCommand.execute(arguments);
    }

    public boolean executeScript(String[] arguments) {
        return executeScriptCommand.execute(arguments);
    }

    public boolean exit(String[] arguments) {
        return exitCommand.execute(arguments);
    }

    public boolean removeGreater(String[] arguments) {
        return removeGreaterCommand.execute(arguments);
    }

    public boolean removeLower(String[] arguments) {
        return removeLowerCommand.execute(arguments);
    }

    public boolean removeGreaterKey(String[] arguments) {
        return removeGreaterKeyCommand.execute(arguments);
    }

    public boolean removeAllByEnginePower(String[] arguments) {
        return removeAllByEnginePowerCommand.execute(arguments);
    }

    public boolean countByFuelType(String[] arguments) {
        return countByFuelTypeCommand.execute(arguments);
    }

    public boolean filterLessThanFuelType(String[] arguments) {
        return filterLessThanFuelTypeCommand.execute(arguments);
    }
}
