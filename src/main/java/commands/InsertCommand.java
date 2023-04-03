package commands;

import data.Vehicle;
import processing.BufferedDataBase;
import mods.ExecuteMode;

public class InsertCommand implements Command {
    private BufferedDataBase bufferedDataBase;
    private static final String NAME = "insert";
    private static final String DESCRIPTION =
            "добавляет новый элемент с заданным ключом";
    private static final int COUNT_OF_EXTRA_ARGUMENTS = Vehicle.getCountOfChangeableFields();
    public InsertCommand(BufferedDataBase bufferedDataBase) {
        this.bufferedDataBase = bufferedDataBase;
    }

    @Override
    public boolean execute(String[] arguments, String[] vehicleValues, ExecuteMode executeMode) {
        return bufferedDataBase.insert(arguments, vehicleValues, executeMode);
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