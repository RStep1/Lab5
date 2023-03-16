package commands;

import processing.BufferedDataBase;
import processing.ExecuteMode;

public class RemoveGreaterKeyCommand implements Command {
    private BufferedDataBase bufferedDataBase;
    private static final String NAME = "remove_greater_key";
    private static final String DESCRIPTION =
            "удаляет из коллекции все элементы, " +
                    "ключ которых превышает заданный";
    public RemoveGreaterKeyCommand(BufferedDataBase bufferedDataBase) {
        this.bufferedDataBase = bufferedDataBase;
    }

    @Override
    public boolean execute(String[] arguments, ExecuteMode executeMode) {
        return bufferedDataBase.removeGreaterKey(arguments);
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