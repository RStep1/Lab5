package commands;

import processing.BufferedDataBase;

public class ClearCommand implements Command {
    private BufferedDataBase bufferedDataBase;
    private static final String NAME = "clear";
    private static final String DESCRIPTION = "очищает коллекцию";
    public ClearCommand(BufferedDataBase bufferedDataBase) {
        this.bufferedDataBase = bufferedDataBase;
    }

    @Override
    public boolean execute(String[] arguments) {
        return bufferedDataBase.clear(arguments);
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
