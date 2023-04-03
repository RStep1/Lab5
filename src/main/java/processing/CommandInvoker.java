package processing;

import commands.*;
import mods.ExecuteMode;
import utility.FileHandler;

import java.util.ArrayList;

public class CommandInvoker {
    private ArrayList<Command> commandList = new ArrayList<>();

    public CommandInvoker(Command helpCommand, Command infoCommand, Command showCommand,
                          Command insertCommand, Command updateCommand, Command removeKeyCommand,
                          Command clearCommand, Command saveCommand, Command executeScriptCommand,
                          Command exitCommand, Command removeGreaterCommand, Command removeLowerCommand,
                          Command removeGreaterKeyCommand, Command removeAllByEnginePowerCommand,
                          Command countByFuelTypeCommand, Command filterLessThanFuelTypeCommand) {
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
        StringBuilder reference = new StringBuilder();
        for (Command command : commandList) {
            reference.append(command).append("\n");
        }
        return reference.toString();
    }

    private void setReferenceFile() {
        FileHandler.writeReferenceFile(getCommandDescription());
    }

    public boolean help(String[] arguments, String[] vehicleValues, ExecuteMode executeMode) {
        for (Command command : commandList) {
            if (command instanceof HelpCommand helpCommand) {
                return helpCommand.execute(arguments, vehicleValues, executeMode);
            }
        }
        return false;
    }

    public boolean info(String[] arguments, String[] vehicleValues, ExecuteMode executeMode) {
        for (Command command : commandList) {
            if (command instanceof InfoCommand infoCommand) {
                return infoCommand.execute(arguments, vehicleValues, executeMode);
            }
        }
        return false;
    }

    public boolean show(String[] arguments, String[] vehicleValues, ExecuteMode executeMode) {
        for (Command command : commandList) {
            if (command instanceof ShowCommand showCommand) {
                return showCommand.execute(arguments, vehicleValues, executeMode);
            }
        }
        return false;
    }

    public boolean insert(String[] arguments, String[] vehicleValues, ExecuteMode executeMode) {
        for (Command command : commandList) {
            if (command instanceof InsertCommand insertCommand) {
                return insertCommand.execute(arguments, vehicleValues, executeMode);
            }
        }
        return false;
    }

    public boolean update(String[] arguments, String[] vehicleValues, ExecuteMode executeMode) {
        for (Command command : commandList) {
            if (command instanceof UpdateCommand updateCommand) {
                return updateCommand.execute(arguments, vehicleValues, executeMode);
            }
        }
        return false;
    }

    public boolean removeKey(String[] arguments, String[] vehicleValues, ExecuteMode executeMode) {
        for (Command command : commandList) {
            if (command instanceof RemoveKeyCommand removeKeyCommand) {
                return removeKeyCommand.execute(arguments, vehicleValues, executeMode);
            }
        }
        return false;
    }

    public boolean clear(String[] arguments, String[] vehicleValues, ExecuteMode executeMode) {
        for (Command command : commandList) {
            if (command instanceof ClearCommand clearCommand) {
                return clearCommand.execute(arguments, vehicleValues, executeMode);
            }
        }
        return false;
    }

    public boolean save(String[] arguments, String[] vehicleValues, ExecuteMode executeMode) {
        for (Command command : commandList) {
            if (command instanceof SaveCommand saveCommand) {
                return saveCommand.execute(arguments, vehicleValues, executeMode);
            }
        }
        return false;
    }

    public boolean executeScript(String[] arguments, String[] vehicleValues, ExecuteMode executeMode) {
        for (Command command : commandList) {
            if (command instanceof ExecuteScriptCommand executeScriptCommand) {
                return executeScriptCommand.execute(arguments, vehicleValues, executeMode);
            }
        }
        return false;
    }

    public boolean exit(String[] arguments, String[] vehicleValues, ExecuteMode executeMode) {
        for (Command command : commandList) {
            if (command instanceof ExitCommand exitCommand) {
                return exitCommand.execute(arguments, vehicleValues, executeMode);
            }
        }
        return false;
    }

    public boolean removeGreater(String[] arguments, String[] vehicleValues, ExecuteMode executeMode) {
        for (Command command : commandList) {
            if (command instanceof RemoveGreaterCommand removeGreaterCommand) {
                return removeGreaterCommand.execute(arguments, vehicleValues, executeMode);
            }
        }
        return false;
    }

    public boolean removeLower(String[] arguments, String[] vehicleValues, ExecuteMode executeMode) {
        for (Command command : commandList) {
            if (command instanceof RemoveLowerCommand removeLowerCommand) {
                return removeLowerCommand.execute(arguments, vehicleValues, executeMode);
            }
        }
        return false;
    }

    public boolean removeGreaterKey(String[] arguments, String[] vehicleValues, ExecuteMode executeMode) {
        for (Command command : commandList) {
            if (command instanceof RemoveGreaterKeyCommand removeGreaterKeyCommand) {
                return removeGreaterKeyCommand.execute(arguments, vehicleValues, executeMode);
            }
        }
        return false;
    }

    public boolean removeAllByEnginePower(String[] arguments, String[] vehicleValues, ExecuteMode executeMode) {
        for (Command command : commandList) {
            if (command instanceof RemoveAllByEnginePowerCommand removeAllByEnginePowerCommand) {
                return removeAllByEnginePowerCommand.execute(arguments, vehicleValues, executeMode);
            }
        }
        return false;
    }

    public boolean countByFuelType(String[] arguments, String[] vehicleValues, ExecuteMode executeMode) {
        for (Command command : commandList) {
            if (command instanceof CountByFuelTypeCommand countByFuelTypeCommand) {
                return countByFuelTypeCommand.execute(arguments, vehicleValues, executeMode);
            }
        }
        return false;
    }

    public boolean filterLessThanFuelType(String[] arguments, String[] vehicleValues, ExecuteMode executeMode) {
        for (Command command : commandList) {
            if (command instanceof FilterLessThanFuelTypeCommand filterLessThanFuelTypeCommand) {
                return filterLessThanFuelTypeCommand.execute(arguments, vehicleValues, executeMode);
            }
        }
        return false;
    }
}
