package commands;

import processing.BufferedDataBase;
import processing.ExecuteMode;

public class InfoCommand implements Command {
    private BufferedDataBase bufferedDataBase;
    private static final String NAME = "info";
    private static final String DESCRIPTION = "выводит информацию о коллекции " +
            "(тип, дата инициализации, количество элементов и т.д.)";
    public InfoCommand(BufferedDataBase bufferedDataBase) {
        this.bufferedDataBase = bufferedDataBase;
    }

    @Override
    public boolean execute(String[] arguments, ExecuteMode executeMode) {
        return bufferedDataBase.info(arguments, executeMode);
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
