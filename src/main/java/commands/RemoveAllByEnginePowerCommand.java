package commands;

import processing.BufferedDataBase;
import mods.ExecuteMode;

/**
 * Acts as a wrapper for the 'remove all by engine power' command.
 * Calls the method containing the implementation of this command.
 */
public class RemoveAllByEnginePowerCommand implements Command {
    private BufferedDataBase bufferedDataBase;
    private static final String NAME = "remove_all_by_engine_power";
    private static final String ARGUMENTS = " <enginePower>";
    private static final String DESCRIPTION =
            "removes from the collection all elements whose enginePower field value is equivalent to the given one";
    private static final int COUNT_OF_EXTRA_ARGUMENTS = 0;
    public RemoveAllByEnginePowerCommand(BufferedDataBase bufferedDataBase) {
        this.bufferedDataBase = bufferedDataBase;
    }

    @Override
    public boolean execute(String[] arguments, String[] vehicleValues, ExecuteMode executeMode) {
        return bufferedDataBase.removeAllByEnginePower(arguments, vehicleValues, executeMode);
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
        return NAME + ARGUMENTS + ": " + DESCRIPTION;
    }
}
