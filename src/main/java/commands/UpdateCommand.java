package commands;

import processing.BufferedDataBase;
import processing.ExecuteMode;

public class UpdateCommand implements Command {
    private BufferedDataBase bufferedDataBase;
    private static final String NAME = "update";
    private static final String DESCRIPTION =
            "обновляет значение элемента коллекции, " +
                    "id которого равен заданному";
    public UpdateCommand(BufferedDataBase bufferedDataBase) {
        this.bufferedDataBase = bufferedDataBase;
    }

    @Override
    public boolean execute(String[] arguments, ExecuteMode executeMode) {
        return bufferedDataBase.update(arguments, executeMode);
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
