package commands;

import processing.BufferedDataBase;
import processing.ExecuteMode;

public class RemoveAllByEnginePowerCommand implements Command {
    private BufferedDataBase bufferedDataBase;
    private static final String NAME = "remove_all_by_engine_power";
    private static final String DESCRIPTION =
            " удаляет из коллекции все элементы, значение " +
                    "поля enginePower которого эквивалентно заданному";
    public RemoveAllByEnginePowerCommand(BufferedDataBase bufferedDataBase) {
        this.bufferedDataBase = bufferedDataBase;
    }

    @Override
    public boolean execute(String[] arguments, ExecuteMode executeMode) {
        return bufferedDataBase.removeAllByEnginePower(arguments, executeMode);
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
