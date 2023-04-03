package commands;

import processing.BufferedDataBase;
import mods.ExecuteMode;

public class SaveCommand implements Command {
    private BufferedDataBase bufferedDataBase;
    private static final String NAME = "save";
    private static final String DESCRIPTION =
            "сохраняет коллекцию в файл";
    private static final int COUNT_OF_EXTRA_ARGUMENTS = 0;

    public SaveCommand(BufferedDataBase bufferedDataBase) {
        this.bufferedDataBase = bufferedDataBase;
    }

    @Override
    public boolean execute(String[] arguments, String[] vehicleValues, ExecuteMode executeMode) {
        return bufferedDataBase.save(arguments, vehicleValues, executeMode);
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
