package processing;

import data.Coordinates;
import data.Vehicle;
import org.codehaus.jackson.sym.NameN;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.regex.Pattern;


public class CollectionHandler {
    private final Hashtable<Long, Vehicle> dataBase;
    private static final int ID_LENGTH = 10;
    private static final int MAX_KEY_LENGTH = 10;
    private final ExecuteMode executeMode;

    public CollectionHandler(Hashtable<Long, Vehicle> dataBase, ExecuteMode executeMode) {
        this.dataBase = dataBase;
        this.executeMode = executeMode;
    }

    public ExecuteMode getExecuteMode() {
        return executeMode;
    }

    private boolean hasNonNumericCharacters(String value, String valueName) {
        String nonDigitValue = "-?\\d+";
        Pattern nonDigitValuePattern = Pattern.compile(nonDigitValue);
        if (nonDigitValuePattern.matcher(value).matches()) {
            return false;
        }
        FileHandler.writeUserErrors(String.format("%s must be a number", valueName));
        return true;
    }
    private boolean isNegativeValue(String value, String valueName) {
        String nonPositiveValue = "-\\d+";
        Pattern nonPositiveValuePattern = Pattern.compile(nonPositiveValue);
        if (nonPositiveValuePattern.matcher(value).matches()) {
            FileHandler.writeUserErrors(String.format("%s cannot be negative", valueName));
            return true;
        }
        return false;
    }
    private boolean hasLeadingZeros(String value, String valueName) {
        String leadingZeros = "^0+\\d+";
        Pattern leadingZerosPattern = Pattern.compile(leadingZeros);
        if (leadingZerosPattern.matcher(value).matches()) {
            FileHandler.writeUserErrors(String.format("%s cannot have leading zeros", valueName));
            return true;
        }
        return false;
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

    public boolean checkKey(String key) {
        if (hasNonNumericCharacters(key, "Key"))
            return false;
        if (isNegativeValue(key, "Key"))
            return false;
        if (hasLeadingZeros(key, "key"))
            return false;
        if (key.length() > MAX_KEY_LENGTH) {
            FileHandler.writeUserErrors(String.format("key is too long, max length - %s", MAX_KEY_LENGTH));
            return false;
        }
        if (dataBase.containsKey(Long.parseLong(key))) {
            FileHandler.writeUserErrors("element with the same key already exists");
            return false;
        }
        return true;
    }

    public boolean checkId(String id) {
        if (hasNonNumericCharacters(id, "id"))
            return false;
        if (isNegativeValue(id, "id"))
            return false;
        if (hasLeadingZeros(id, "id"))
            return false;
        if (id.length() != ID_LENGTH) {
            FileHandler.writeUserErrors(String.format("invalid id length: %s, expected %s",
                    id.length(), ID_LENGTH));
            return false;
        }
        if (hasElementWithId(Long.parseLong(id))) {
            FileHandler.writeUserErrors("element with the same id already exists");
            return false;
        }
        return true;
    }

    private boolean hasElementWithId(long id) {
        Enumeration<Long> keys = dataBase.keys();
        while (keys.hasMoreElements()) {
            Long key = keys.nextElement();
            if (id == dataBase.get(key).getId()) {
                return true;
            }
        }
        return false;
    }

    public Long generateId() {
        StringBuilder newId = new StringBuilder(ID_LENGTH);
        do {
//            newId.replace(0, NUMBER_OF_ID_DIGITS, "");
            newId = new StringBuilder(ID_LENGTH);
//            newId.append((int) (Math.random() * 9 + 1));
            for (int i = 0; i < ID_LENGTH; i++) {
                newId.append((long) (Math.random() * 10));
            }
        } while (hasElementWithId(Long.parseLong(newId.toString())));
        return Long.parseLong(newId.toString());
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
        try { ////////////////////////////////////////////////////////////////////////////////////////////////////////
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
        //Поле не может быть null
        //проверка регулярками

        if (newVehicleType.equals("")) {
            FileHandler.writeUserErrors("Vehicle type cannot be null");
            return false;
        }
        int vehicleType = Integer.parseInt(newVehicleType);
        //выбор значения из enum
        return true;
    }
    public boolean checkFuelType(String newFuelType) {
        //Поле не может быть null
        //проверка регулярками

        if (newFuelType.equals("")) {
            FileHandler.writeUserErrors("Fuel type cannot be null");
            return false;
        }
        int fuelType = Integer.parseInt(newFuelType);
        //проверка существования значения в enum
        return true;
    }
}
