package commands;

import processing.BufferedDataBase;
import mods.ExecuteMode;

public class ExecuteScriptCommand implements Command {
    private BufferedDataBase bufferedDataBase;
    private static final String NAME = "execute_script";
    private static final String DESCRIPTION =
            "считывает и исполняет скрипт из указанного файла. " +
                    "В скрипте содержатся команды в таком же виде, " +
                    "в котором их вводит пользователь в интерактивном режиме.";
    private static final int COUNT_OF_EXTRA_ARGUMENTS = 0;
    public ExecuteScriptCommand(BufferedDataBase bufferedDataBase) {
        this.bufferedDataBase = bufferedDataBase;
    }

    @Override
    public boolean execute(String[] arguments, String[] vehicleValues, ExecuteMode executeMode) {
        return bufferedDataBase.executeScript(arguments, vehicleValues, executeMode);
    }

    public static String getName() {
        return NAME;
    }

    public static String getDescription() {
        return DESCRIPTION;
    }

    public static int getCountOfExtraArguments() {
        return COUNT_OF_EXTRA_ARGUMENTS;
    }

    @Override
    public String toString() {
        return NAME + ": " + DESCRIPTION;
    }
}
