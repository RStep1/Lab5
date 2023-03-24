package commands;

import processing.BufferedDataBase;
import mods.ExecuteMode;

public class ShowCommand implements Command {
    private BufferedDataBase bufferedDataBase;
    private static final String NAME = "show";
    private static final String DESCRIPTION =
            "выводит в стандартный поток вывода все элементы" +
                    " коллекции в строковом представлении";
    public ShowCommand(BufferedDataBase bufferedDataBase) {
        this.bufferedDataBase = bufferedDataBase;
    }

    @Override
    public boolean execute(String[] arguments, ExecuteMode executeMode) {
        return bufferedDataBase.show(arguments, executeMode);
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
