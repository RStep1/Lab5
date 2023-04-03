package commands;

import processing.BufferedDataBase;
import mods.ExecuteMode;

public class CountByFuelTypeCommand implements Command {
    private BufferedDataBase bufferedDataBase;
    private static final String NAME = "count_by_fuel_type";
    private static final String DESCRIPTION =
            "выводит количество элементов, значение поля" +
                    " fuelType которых равно заданному";
    private static final int COUNT_OF_EXTRA_ARGUMENTS = 0;
    public CountByFuelTypeCommand(BufferedDataBase bufferedDataBase) {
        this.bufferedDataBase = bufferedDataBase;
    }

    @Override
    public boolean execute(String[] arguments, String[] vehicleValues, ExecuteMode executeMode) {
        return bufferedDataBase.countByFuelType(arguments,vehicleValues , executeMode);
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
