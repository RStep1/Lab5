package commands;

import processing.BufferedDataBase;
import mods.ExecuteMode;

public class ClearCommand implements Command {
    private BufferedDataBase bufferedDataBase;
    private static final String NAME = "clear";
    private static final String DESCRIPTION = "очищает коллекцию";
    private static final int COUNT_OF_EXTRA_ARGUMENTS = 0;

    public ClearCommand(BufferedDataBase bufferedDataBase) {
        this.bufferedDataBase = bufferedDataBase;
    }

    @Override
    public boolean execute(String[] arguments, String[] vehicleValues, ExecuteMode executeMode) {
        return bufferedDataBase.clear(arguments, vehicleValues, executeMode);
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
