package commands;

import processing.BufferedDataBase;

public class ExecuteScriptCommand implements Command {
    private BufferedDataBase bufferedDataBase;
    private static final String NAME = "execute_script";
    private static final String DESCRIPTION =
            "считывает и исполняет скрипт из указанного файла. " +
                    "В скрипте содержатся команды в таком же виде, " +
                    "в котором их вводит пользователь в интерактивном режиме.";
    public ExecuteScriptCommand(BufferedDataBase bufferedDataBase) {
        this.bufferedDataBase = bufferedDataBase;
    }

    @Override
    public boolean execute(String[] arguments) {
        return bufferedDataBase.executeScript(arguments);
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
