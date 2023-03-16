package commands;

import processing.BufferedDataBase;
import processing.ExecuteMode;

public class RemoveLowerCommand implements Command {
    private BufferedDataBase bufferedDataBase;
    private static final String NAME = "remove_lower";
    private static final String DESCRIPTION =
            "удаляет из коллекции все элементы, меньшие, чем заданный";
    public RemoveLowerCommand(BufferedDataBase bufferedDataBase) {
        this.bufferedDataBase = bufferedDataBase;
    }

    @Override
    public boolean execute(String[] arguments, ExecuteMode executeMode) {
        return bufferedDataBase.removeLower(arguments);
    }

    public static String getName() {
        return NAME;
    }

    public static String getDescription() {
        return DESCRIPTION;
    }

    @Override
    public String toString() {
        return NAME + ": " + DESCRIPTION;
    }
}
