package commands;

import processing.BufferedDataBase;
import processing.ExecuteMode;

public class RemoveGreaterCommand implements Command {
    private BufferedDataBase bufferedDataBase;
    private static final String NAME = "remove_greater";
    private static final String DESCRIPTION =
            "удаляет из коллекции все элементы, превышающие заданный";
    public RemoveGreaterCommand(BufferedDataBase bufferedDataBase) {
        this.bufferedDataBase = bufferedDataBase;
    }

    @Override
    public boolean execute(String[] arguments, ExecuteMode executeMode) {
        return bufferedDataBase.removeGreater(arguments);
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
