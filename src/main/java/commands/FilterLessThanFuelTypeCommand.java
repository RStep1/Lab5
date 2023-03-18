package commands;

import processing.BufferedDataBase;
import processing.ExecuteMode;

public class FilterLessThanFuelTypeCommand implements Command {
    private BufferedDataBase bufferedDataBase;
    private static final String NAME =
            "filter_less_than_fuel_type";
    private static final String DESCRIPTION =
            "выводит элементы, значение поля " +
                    "fuelType которых меньше заданного";
    public FilterLessThanFuelTypeCommand(BufferedDataBase bufferedDataBase) {
        this.bufferedDataBase = bufferedDataBase;
    }

    @Override
    public boolean execute(String[] arguments, ExecuteMode executeMode) {
        return bufferedDataBase.filterLessThanFuelType(arguments, executeMode);
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
