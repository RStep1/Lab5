package processing;

import data.Coordinates;
import data.FuelType;
import data.Vehicle;
import data.VehicleType;
import org.codehaus.jackson.sym.NameN;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.regex.Pattern;


public class CollectionHandler {
    private final Hashtable<Long, Vehicle> dataBase;
    private final ExecuteMode executeMode;

    public CollectionHandler(Hashtable<Long, Vehicle> dataBase, ExecuteMode executeMode) {
        this.dataBase = dataBase;
        this.executeMode = executeMode;
    }

    public ExecuteMode getExecuteMode() {
        return executeMode;
    }

    private boolean checkTypeValue(ValueType valueType, String value, String valueName) {
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

    public boolean checkName(String name) {
        if (name == null || name.equals("")) {
            FileHandler.writeUserErrors("Name cannot be null");
            return false;
        }
        return true;
    }
    public boolean checkX(String newX) {
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
    }
    public boolean checkY(String newY) {
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
    }

    public boolean checkEnginePower(String newEnginePower) {
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
    }
    public boolean checkDistanceTravelled(String newDistanceTravelled) {
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
    }
    public boolean checkVehicleType(String newVehicleType) {
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
    }
    public boolean checkFuelType(String newFuelType) {
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
    }
}
