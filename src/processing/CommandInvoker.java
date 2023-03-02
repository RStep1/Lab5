package processing;

import commands.Command;
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
    //запихнуть это в все в array

    private ArrayList<Command> commandList = new ArrayList<>();

    public CommandInvoker(Command helpCommand, Command infoCommand, Command exitCommand/*, Command showCommand,
                          Command insertCommand, Command updateCommand, Command removeKeyCommand,
                          Command clearCommand, Command saveCommand, Command executeScriptCommand,
                          Command exitCommand, Command removeGreaterCommand, Command removeLowerCommand,
                          Command removeGreaterKeyCommand, Command removeAllByEnginePowerCommand,
                          Command countByFuelTypeCommand, Command filterLessThanFuelTypeCommand*/) {
        this.helpCommand = helpCommand;
        this.infoCommand = infoCommand;

        this.exitCommand = exitCommand;
        /*
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
        */

        commandList.add(helpCommand);
        commandList.add(infoCommand);
        /*
        commandList.add(showCommand);
        commandList.add(insertCommand);
        commandList.add(updateCommand);
        commandList.add(removeKeyCommand);
        commandList.add(clearCommand);
        commandList.add(saveCommand);
        commandList.add(executeScriptCommand);
        */
        commandList.add(exitCommand);
        /*
        commandList.add(removeGreaterCommand);
        commandList.add(removeLowerCommand);
        commandList.add(removeGreaterKeyCommand);
        commandList.add(removeAllByEnginePowerCommand);
        commandList.add(countByFuelTypeCommand);
        commandList.add(filterLessThanFuelTypeCommand);

         */
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

    /*
    public boolean show(String[] arguments) {
        return showCommand.execute(arguments);
    }

    public boolean insert(String argument) {
        return insertCommand.execute(argument);
    }

    public boolean update(String argument) {
        return updateCommand.execute(argument);
    }

    public boolean removeKey(String argument) {
        return removeKeyCommand.execute(argument);
    }

    public boolean clear(String argument) {
        return clearCommand.execute(argument);
    }

    public boolean save(String argument) {
        return saveCommand.execute(argument);
    }

    public boolean executeScript(String argument) {
        return executeScriptCommand.execute(argument);
    }
    */

    public boolean exit(String[] arguments) {
        return exitCommand.execute(arguments);
    }
    /*
    public boolean removeGreater(String argument) {
        return removeGreaterCommand.execute(argument);
    }

    public boolean removeLower(String argument) {
        return removeLowerCommand.execute(argument);
    }

    public boolean removeGreaterKey(String argument) {
        return removeGreaterKeyCommand.execute(argument);
    }

    public boolean removeAllByEnginePower(String argument) {
        return removeAllByEnginePowerCommand.execute(argument);
    }

    public boolean countByFuelType(String argument) {
        return countByFuelTypeCommand.execute(argument);
    }

    public boolean filterLessThanFuelType(String argument) {
        return filterLessThanFuelTypeCommand.execute(argument);
    }

     */

}
