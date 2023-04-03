package commands;

import processing.BufferedDataBase;
import mods.ExecuteMode;

public class FilterLessThanFuelTypeCommand implements Command {
    private BufferedDataBase bufferedDataBase;
    private static final String NAME =
            "filter_less_than_fuel_type";
    private static final String DESCRIPTION =
            "выводит элементы, значение поля " +
                    "fuelType которых меньше заданного";
    private static final int COUNT_OF_EXTRA_ARGUMENTS = 0;
    public FilterLessThanFuelTypeCommand(BufferedDataBase bufferedDataBase) {
        this.bufferedDataBase = bufferedDataBase;
    }

    @Override
    public boolean execute(String[] arguments, String[] vehicleValues, ExecuteMode executeMode) {
        return bufferedDataBase.filterLessThanFuelType(arguments, vehicleValues, executeMode);
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
