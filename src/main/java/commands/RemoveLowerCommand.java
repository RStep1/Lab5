package commands;

import processing.BufferedDataBase;
import mods.ExecuteMode;

public class RemoveLowerCommand implements Command {
    private BufferedDataBase bufferedDataBase;
    private static final String NAME = "remove_lower";
    private static final String DESCRIPTION =
            "удаляет из коллекции все элементы, меньшие, чем заданный";
    private static final int COUNT_OF_EXTRA_ARGUMENTS = 0;

    public RemoveLowerCommand(BufferedDataBase bufferedDataBase) {
        this.bufferedDataBase = bufferedDataBase;
    }

    @Override
    public boolean execute(String[] arguments, ExecuteMode executeMode) {
        return bufferedDataBase.removeLower(arguments, executeMode);
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
