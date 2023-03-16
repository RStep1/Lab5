package commands;

import processing.BufferedDataBase;
import processing.ExecuteMode;

public class InsertCommand implements Command {
    private BufferedDataBase bufferedDataBase;
    private static final String NAME = "insert";
    private static final String DESCRIPTION =
            "добавляет новый элемент с заданным ключом";
    public InsertCommand(BufferedDataBase bufferedDataBase) {
        this.bufferedDataBase = bufferedDataBase;
    }

    @Override
    public boolean execute(String[] arguments, ExecuteMode executeMode) {
        return bufferedDataBase.insert(arguments);
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