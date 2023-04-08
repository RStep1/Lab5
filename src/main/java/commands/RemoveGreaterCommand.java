package commands;

import processing.BufferedDataBase;
import mods.ExecuteMode;

/**
 * Acts as a wrapper for the 'remove greater' command.
 * Calls the method containing the implementation of this command.
 */
public class RemoveGreaterCommand implements Command {
    private BufferedDataBase bufferedDataBase;
    private static final String NAME = "remove_greater";
    private static final String DESCRIPTION =
            "удаляет из коллекции все элементы, превышающие заданный";
    private static final int COUNT_OF_EXTRA_ARGUMENTS = 0;

    public RemoveGreaterCommand(BufferedDataBase bufferedDataBase) {
        this.bufferedDataBase = bufferedDataBase;
    }

    @Override
    public boolean execute(String[] arguments, String[] vehicleValues, ExecuteMode executeMode) {
        return bufferedDataBase.removeGreater(arguments, vehicleValues, executeMode);
    }

    public static String getName() {
        return NAME;
    }

    public static String getDescription() {
        return DESCRIPTION;
    }

    public static int getCountOfExtraArguments() {
        return COUNT_OF_EXTRA_ARGUMENTS;
    }

    @Override
    public String toString() {
        return NAME + ": " + DESCRIPTION;
    }
}
