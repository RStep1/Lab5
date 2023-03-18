package commands;
import processing.BufferedDataBase;
import processing.ExecuteMode;

public class ExitCommand implements Command {
    private BufferedDataBase dataBase;
    private static final String NAME = "exit";
    private static final String DESCRIPTION =
            "завершает программу без сохранения в файл";
    public ExitCommand(BufferedDataBase dataBase) {
        this.dataBase = dataBase;
    }

    @Override
    public boolean execute(String[] arguments, ExecuteMode executeMode) {
        return dataBase.exit(arguments, executeMode);
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
