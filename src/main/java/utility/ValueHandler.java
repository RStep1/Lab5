package utility;


import data.Coordinates;
import data.FuelType;
import data.VehicleType;
import mods.ValueType;
import processing.FileHandler;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class ValueHandler {
    private static ArrayList<Process> valueProcesses = new ArrayList<>();

    public static ArrayList<Process> getValueProcesses() {
        valueProcesses.add(NAME_PROCESSING);
        valueProcesses.add(X_COORDINATE_PROCESSING);
        valueProcesses.add(Y_COORDINATE_PROCESSING);
        valueProcesses.add(ENGINE_POWER_PROCESSING);
        valueProcesses.add(DISTANCE_TRAVELLED_PROCESSING);
        valueProcesses.add(VEHICLE_TYPE_PROCESSING);
        valueProcesses.add(FUEL_TYPE_PROCESSING);
        return valueProcesses;
    }

    private static boolean checkTypeValue(ValueType valueType, String value, String valueName) {
        Pattern valuePattern = Pattern.compile("");
        if (valueType == ValueType.DOUBLE || valueType == ValueType.FLOAT)
            valuePattern = Pattern.compile("([-+]?\\d*[.,]?\\d*)");
        if (valueType == ValueType.LONG || valueType == ValueType.INTEGER)
            valuePattern = Pattern.compile("[-+]?\\d+");
        if (valuePattern.matcher(value).matches())
            return true;
        FileHandler.writeUserErrors(String.format("%s must be of type %s", valueName, valueType.getName()));
        return false;
    }

    public static final Correction NAME_CORRECTION = String::trim;
    public static final Correction NUMBER_VALUE_CORRECTION =
            value -> value.trim().replaceAll(",", ".").replaceAll("\\+", "");
    public static final Correction TYPE_CORRECTION = value -> value.trim().toUpperCase();
    public static final Checker NAME_CHECKER = name -> {
        if (name == null || name.equals("")) {
            FileHandler.writeUserErrors("Name cannot be null");
            return false;
        }
        return true;
    };
    public static final Checker X_COORDINATE_CHECKER = newX -> {
        if (!checkTypeValue(ValueType.FLOAT, newX, "X coordinate"))
            return false;
        float x = Float.parseFloat(newX);
        try {
            float truncatedX = BigDecimal.valueOf(x).setScale(Coordinates.getAccuracy(),
                    RoundingMode.HALF_UP).floatValue();
            if (Float.compare(truncatedX, 341f) == 1) { // truncatedX > 341
                FileHandler.writeUserErrors("Max X value: 341");
                return false;
            }
        } catch (NumberFormatException e) {
            FileHandler.writeUserErrors("Float value overflowed");
            return false;
        }
        return true;
    };
    public static final Checker Y_COORDINATE_CHECKER = newY -> {
        if (!checkTypeValue(ValueType.DOUBLE, newY, "Y coordinate"))
            return false;
        double y = Double.parseDouble(newY);
        try {
            double truncatedY = BigDecimal.valueOf(y).setScale(Coordinates.getAccuracy(),
                    RoundingMode.HALF_UP).doubleValue();
            if (Double.compare(truncatedY, -272d) != 1) { // truncatedY <= 272
                FileHandler.writeUserErrors("Y must be grater than -272");
                return false;
            }
        } catch (NumberFormatException e) {
            FileHandler.writeUserErrors("Double value overflowed");
            return false;
        }
        return true;
    };
    public static final Checker ENGINE_POWER_CHECKER = newEnginePower -> {
        if (!checkTypeValue(ValueType.INTEGER, newEnginePower, "Engine power"))
            return false;
        try {
            int enginePower = Integer.parseInt(newEnginePower);
            if (enginePower <= 0) {
                FileHandler.writeUserErrors("Engine power must be greater than 0");
                return false;
            }
        } catch (NumberFormatException e) {
            FileHandler.writeUserErrors("Integer value overflowed");
            return false;
        }
        return true;
    };
    public static final Checker DISTANCE_TRAVELLED_CHECKER = newDistanceTravelled -> {
        if (!checkTypeValue(ValueType.LONG, newDistanceTravelled, "Distance travelled"))
            return false;
        try {
            long distanceTravelled = Long.parseLong(newDistanceTravelled);
            if (distanceTravelled <= 0) {
                FileHandler.writeUserErrors("Distance travelled must be greater than 0");
                return false;
            }
        } catch (NumberFormatException e) {
            FileHandler.writeUserErrors("Long value overflowed");
            return false;
        }
        return true;
    };
    public static final Checker VEHICLE_TYPE_CHECKER = newVehicleType -> {
        if (newVehicleType.equals("")) {
            FileHandler.writeUserErrors("Vehicle type cannot be null");
            return false;
        }
        try {
            VehicleType.valueOf(newVehicleType);
        } catch (IllegalArgumentException illegalArgumentException) {
            try {
                int serialNumber = Integer.parseInt(newVehicleType);
                if (serialNumber < 1 || serialNumber > VehicleType.values().length) {
                    FileHandler.writeUserErrors("No such vehicle type number exits");
                    return false;
                }
            } catch (NumberFormatException numberFormatException) {
                FileHandler.writeUserErrors("This vehicle type does not exist");
                return false;
            }
        }
        return true;
    };
    public static final Checker FUEL_TYPE_CHECKER = newFuelType -> {
        if (newFuelType.equals("")) {
            FileHandler.writeUserErrors("Fuel type cannot be null");
            return false;
        }
        try {
            FuelType.valueOf(newFuelType);
        } catch (IllegalArgumentException illegalArgumentException) {
            try {
                int serialNumber = Integer.parseInt(newFuelType);
                if (serialNumber < 1 || serialNumber > FuelType.values().length) {
                    FileHandler.writeUserErrors("No such fuel type number exits");
                    return false;
                }
            } catch (NumberFormatException numberFormatException) {
                FileHandler.writeUserErrors("This fuel type does not exist");
                return false;
            }
        }
        return true;
    };
    public static final Process NAME_PROCESSING = new Process() {
        @Override
        public String getMessage() {
            return "Enter name: ";
        }

        @Override
        public Correction getCorrection() {
            return NAME_CORRECTION;
        }

        @Override
        public Checker getChecker() {
            return NAME_CHECKER;
        }
    };
    public static final Process X_COORDINATE_PROCESSING = new Process() {
        @Override
        public String getMessage() {
            return "Enter X coordinate: ";
        }

        @Override
        public Correction getCorrection() {
            return NUMBER_VALUE_CORRECTION;
        }

        @Override
        public Checker getChecker() {
            return X_COORDINATE_CHECKER;
        }
    };
    public static final Process Y_COORDINATE_PROCESSING = new Process() {
        @Override
        public String getMessage() {
            return "Enter Y coordinate: ";
        }

        @Override
        public Correction getCorrection() {
            return NUMBER_VALUE_CORRECTION;
        }

        @Override
        public Checker getChecker() {
            return Y_COORDINATE_CHECKER;
        }
    };
    public static final Process ENGINE_POWER_PROCESSING = new Process() {
        @Override
        public String getMessage() {
            return "Enter engine power: ";
        }

        @Override
        public Correction getCorrection() {
            return NUMBER_VALUE_CORRECTION;
        }

        @Override
        public Checker getChecker() {
            return ENGINE_POWER_CHECKER;
        }
    };
    public static final Process DISTANCE_TRAVELLED_PROCESSING = new Process() {
        @Override
        public String getMessage() {
            return "Enter distance travelled: ";
        }

        @Override
        public Correction getCorrection() {
            return NUMBER_VALUE_CORRECTION;
        }

        @Override
        public Checker getChecker() {
            return DISTANCE_TRAVELLED_CHECKER;
        }
    };
    public static final Process VEHICLE_TYPE_PROCESSING = new Process() {
        @Override
        public String getMessage() {
            StringBuilder message = new StringBuilder("Vehicle types:\n");
            for (VehicleType vehicleType : VehicleType.values()) {
                message.append(vehicleType.getSerialNumber()).append(" - ").append(vehicleType).append("\n");
            }
            message.append("Enter vehicle type (numeric value or full name): ");
            return message.toString();
        }

        @Override
        public Correction getCorrection() {
            return TYPE_CORRECTION;
        }

        @Override
        public Checker getChecker() {
            return VEHICLE_TYPE_CHECKER;
        }
    };
    public static final Process FUEL_TYPE_PROCESSING = new Process() {
        @Override
        public String getMessage() {
            StringBuilder message = new StringBuilder("Fuel types:\n");
            for (FuelType fuelType : FuelType.values()) {
                message.append(fuelType.getSerialNumber()).append(" - ").append(fuelType).append("\n");
            }
            message.append("Enter fuel type (numeric value or full name): ");
            return message.toString();
        }

        @Override
        public Correction getCorrection() {
            return TYPE_CORRECTION;
        }

        @Override
        public Checker getChecker() {
            return FUEL_TYPE_CHECKER;
        }
    };
}
