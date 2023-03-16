package commands;

import processing.BufferedDataBase;
import processing.ExecuteMode;

public class RemoveKeyCommand implements Command {
    private BufferedDataBase bufferedDataBase;
    private static final String NAME = "remove_key";
    private static final String DESCRIPTION =
            "удаляет элемент из коллекции по его ключу";
    public RemoveKeyCommand(BufferedDataBase bufferedDataBase) {
        this.bufferedDataBase = bufferedDataBase;
    }

    @Override
    public boolean execute(String[] arguments, ExecuteMode executeMode) {
        return bufferedDataBase.removeKey(arguments);
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
