package commands;

import processing.BufferedDataBase;
import processing.ExecuteMode;

public class CountByFuelTypeCommand implements Command {
    private BufferedDataBase bufferedDataBase;
    private static final String NAME = "count_by_fuel_type";
    private static final String DESCRIPTION =
            "выводит количество элементов, значение поля" +
                    " fuelType которых равно заданному";
    public CountByFuelTypeCommand(BufferedDataBase bufferedDataBase) {
        this.bufferedDataBase = bufferedDataBase;
    }

    @Override
    public boolean execute(String[] arguments, ExecuteMode executeMode) {
        return bufferedDataBase.countByFuelType(arguments, executeMode);
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
