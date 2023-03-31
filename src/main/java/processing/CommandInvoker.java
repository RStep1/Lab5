package processing;

import commands.*;
import mods.ExecuteMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

    public boolean help(String[] arguments, ExecuteMode executeMode) {
        for (Command command : commandList) {
            if (command instanceof HelpCommand) {
                HelpCommand helpCommand = (HelpCommand) command;
                return helpCommand.execute(arguments, executeMode);
            }
        }
        return false;
    }

    public boolean info(String[] arguments, ExecuteMode executeMode) {
        for (Command command : commandList) {
            if (command instanceof InfoCommand) {
                InfoCommand infoCommand = (InfoCommand) command;
                return infoCommand.execute(arguments, executeMode);
            }
        }
        return false;
    }

    public boolean show(String[] arguments, ExecuteMode executeMode) {
        for (Command command : commandList) {
            if (command instanceof ShowCommand) {
                ShowCommand showCommand = (ShowCommand) command;
                return showCommand.execute(arguments, executeMode);
            }
        }
        return false;
    }

    public boolean insert(String[] arguments, ExecuteMode executeMode) {
        for (Command command : commandList) {
            if (command instanceof InsertCommand) {
                InsertCommand insertCommand = (InsertCommand) command;
                return insertCommand.execute(arguments, executeMode);
            }
        }
        return false;
    }

    public boolean update(String[] arguments, ExecuteMode executeMode) {
        for (Command command : commandList) {
            if (command instanceof UpdateCommand) {
                UpdateCommand updateCommand = (UpdateCommand) command;
                return updateCommand.execute(arguments, executeMode);
            }
        }
        return false;
    }

    public boolean removeKey(String[] arguments, ExecuteMode executeMode) {
        for (Command command : commandList) {
            if (command instanceof RemoveKeyCommand) {
                RemoveKeyCommand removeKeyCommand = (RemoveKeyCommand) command;
                return removeKeyCommand.execute(arguments, executeMode);
            }
        }
        return false;
    }

    public boolean clear(String[] arguments, ExecuteMode executeMode) {
        for (Command command : commandList) {
            if (command instanceof ClearCommand) {
                ClearCommand clearCommand = (ClearCommand) command;
                return clearCommand.execute(arguments, executeMode);
            }
        }
        return false;
    }

    public boolean save(String[] arguments, ExecuteMode executeMode) {
        for (Command command : commandList) {
            if (command instanceof SaveCommand) {
                SaveCommand saveCommand = (SaveCommand) command;
                return saveCommand.execute(arguments, executeMode);
            }
        }
        return false;
    }

    public boolean executeScript(String[] arguments, ExecuteMode executeMode) {
        for (Command command : commandList) {
            if (command instanceof ExecuteScriptCommand) {
                ExecuteScriptCommand executeScriptCommand = (ExecuteScriptCommand) command;
                return executeScriptCommand.execute(arguments, executeMode);
            }
        }
        return false;
    }

    public boolean exit(String[] arguments, ExecuteMode executeMode) {
        for (Command command : commandList) {
            if (command instanceof ExitCommand) {
                ExitCommand exitCommand = (ExitCommand) command;
                return exitCommand.execute(arguments, executeMode);
            }
        }
        return false;
    }

    public boolean removeGreater(String[] arguments, ExecuteMode executeMode) {
        for (Command command : commandList) {
            if (command instanceof RemoveGreaterCommand) {
                RemoveGreaterCommand removeGreaterCommand = (RemoveGreaterCommand) command;
                return removeGreaterCommand.execute(arguments, executeMode);
            }
        }
        return false;
    }

    public boolean removeLower(String[] arguments, ExecuteMode executeMode) {
        for (Command command : commandList) {
            if (command instanceof RemoveLowerCommand) {
                RemoveLowerCommand removeLowerCommand = (RemoveLowerCommand) command;
                return removeLowerCommand.execute(arguments, executeMode);
            }
        }
        return false;
    }

    public boolean removeGreaterKey(String[] arguments, ExecuteMode executeMode) {
        for (Command command : commandList) {
            if (command instanceof RemoveGreaterKeyCommand) {
                RemoveGreaterKeyCommand removeGreaterKeyCommand = (RemoveGreaterKeyCommand) command;
                return removeGreaterKeyCommand.execute(arguments, executeMode);
            }
        }
        return false;
    }

    public boolean removeAllByEnginePower(String[] arguments, ExecuteMode executeMode) {
        for (Command command : commandList) {
            if (command instanceof RemoveAllByEnginePowerCommand) {
                RemoveAllByEnginePowerCommand removeAllByEnginePowerCommand =
                        (RemoveAllByEnginePowerCommand) command;
                return removeAllByEnginePowerCommand.execute(arguments, executeMode);
            }
        }
        return false;
    }

    public boolean countByFuelType(String[] arguments, ExecuteMode executeMode) {
        for (Command command : commandList) {
            if (command instanceof CountByFuelTypeCommand) {
                CountByFuelTypeCommand countByFuelTypeCommand = (CountByFuelTypeCommand) command;
                return countByFuelTypeCommand.execute(arguments, executeMode);
            }
        }
        return false;
    }

    public boolean filterLessThanFuelType(String[] arguments, ExecuteMode executeMode) {
        for (Command command : commandList) {
            if (command instanceof FilterLessThanFuelTypeCommand) {
                FilterLessThanFuelTypeCommand filterLessThanFuelTypeCommand =
                        (FilterLessThanFuelTypeCommand) command;
                return filterLessThanFuelTypeCommand.execute(arguments, executeMode);
            }
        }
        return false;
    }
}
