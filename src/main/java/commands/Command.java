package commands;

import processing.ExecuteMode;

public interface Command {
    boolean execute(String[] arguments, ExecuteMode executeMode);
}
