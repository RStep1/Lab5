package processing;

import commands.Command;
import mods.ExecuteMode;

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

        setReferenceFile();
    }

    public ArrayList<Command> getCommandList(){
        return commandList;
    }

    private String getCommandDescription() {
        String reference = "";
        for (Command command : commandList) {
            reference += command + "\n";
        }
        return reference;
    }

    private void setReferenceFile() {
        FileHandler.writeReferenceFile(getCommandDescription());
    }

    public boolean help(String[] arguments, ExecuteMode executeMode) {
        return helpCommand.execute(arguments, executeMode);
    }

    public boolean info(String[] arguments, ExecuteMode executeMode) {
        return infoCommand.execute(arguments, executeMode);
    }

    public boolean show(String[] arguments, ExecuteMode executeMode) {
        return showCommand.execute(arguments, executeMode);
    }

    public boolean insert(String[] arguments, ExecuteMode executeMode) {
        return insertCommand.execute(arguments, executeMode);
    }

    public boolean update(String[] arguments, ExecuteMode executeMode) {
        return updateCommand.execute(arguments, executeMode);
    }

    public boolean removeKey(String[] arguments, ExecuteMode executeMode) {
        return removeKeyCommand.execute(arguments, executeMode);
    }

    public boolean clear(String[] arguments, ExecuteMode executeMode) {
        return clearCommand.execute(arguments, executeMode);
    }

    public boolean save(String[] arguments, ExecuteMode executeMode) {
        return saveCommand.execute(arguments, executeMode);
    }

    public boolean executeScript(String[] arguments, ExecuteMode executeMode) {
        return executeScriptCommand.execute(arguments, executeMode);
    }

    public boolean exit(String[] arguments, ExecuteMode executeMode) {
        return exitCommand.execute(arguments, executeMode);
    }

    public boolean removeGreater(String[] arguments, ExecuteMode executeMode) {
        return removeGreaterCommand.execute(arguments, executeMode);
    }

    public boolean removeLower(String[] arguments, ExecuteMode executeMode) {
        return removeLowerCommand.execute(arguments, executeMode);
    }

    public boolean removeGreaterKey(String[] arguments, ExecuteMode executeMode) {
        return removeGreaterKeyCommand.execute(arguments, executeMode);
    }

    public boolean removeAllByEnginePower(String[] arguments, ExecuteMode executeMode) {
        return removeAllByEnginePowerCommand.execute(arguments, executeMode);
    }

    public boolean countByFuelType(String[] arguments, ExecuteMode executeMode) {
        return countByFuelTypeCommand.execute(arguments, executeMode);
    }

    public boolean filterLessThanFuelType(String[] arguments, ExecuteMode executeMode) {
        return filterLessThanFuelTypeCommand.execute(arguments, executeMode);
    }
}
