package commands;

import processing.BufferedDataBase;
import mods.ExecuteMode;

public class SaveCommand implements Command {
    private BufferedDataBase bufferedDataBase;
    private static final String NAME = "save";
    private static final String DESCRIPTION =
            "сохраняет коллекцию в файл";
    public SaveCommand(BufferedDataBase bufferedDataBase) {
        this.bufferedDataBase = bufferedDataBase;
    }

    @Override
    public boolean execute(String[] arguments, ExecuteMode executeMode) {
        return bufferedDataBase.save(arguments, executeMode);
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
