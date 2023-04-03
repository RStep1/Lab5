package commands;

import mods.ExecuteMode;

public interface Command {
    boolean execute(String[] arguments, String[] extraArguments, ExecuteMode executeMode);
}
