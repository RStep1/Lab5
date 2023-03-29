package commands;
import processing.BufferedDataBase;
import mods.ExecuteMode;

public class HelpCommand implements Command {
    private BufferedDataBase dataBase;
    private static final String NAME = "help";
    private static final String DESCRIPTION =
            "выводит справку по доступным командам";
    private static final int COUNT_OF_EXTRA_ARGUMENTS = 0;
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

    public static int getCountOfExtraArguments() {
        return COUNT_OF_EXTRA_ARGUMENTS;
    }

    @Override
    public String toString() {
        return NAME + ": " + DESCRIPTION;
    }
}
