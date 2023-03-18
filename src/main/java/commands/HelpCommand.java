package commands;
import processing.BufferedDataBase;
import processing.ExecuteMode;

public class HelpCommand implements Command {
    private BufferedDataBase dataBase;
    private static final String NAME = "help";
    private static final String DESCRIPTION =
            "выводит справку по доступным командам";
    public HelpCommand(BufferedDataBase dataBase) {
        this.dataBase = dataBase;
    }

    @Override
    public boolean execute(String[] arguments, ExecuteMode executeMode) {
        return dataBase.help(arguments, executeMode);
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
