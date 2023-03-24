package commands;

import mods.ExecuteMode;

public interface Command {
    boolean execute(String[] arguments, ExecuteMode executeMode);
}
