package commands;

import processing.BufferedDataBase;
import mods.ExecuteMode;


/**
 * Acts as a wrapper for the 'filter less than fuel type' command.
 * Calls the method containing the implementation of this command.
 */
public class FilterLessThanFuelTypeCommand implements Command {
    private BufferedDataBase bufferedDataBase;
    private static final String NAME = "filter_less_than_fuel_type";
    private static final String ARGUMENTS = " <fuelType>";
    private static final String DESCRIPTION = "displays elements whose fuelType field value is less than the given one";
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
        return NAME + ARGUMENTS + ": " + DESCRIPTION;
    }
}