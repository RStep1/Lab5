package processing;

import data.Vehicle;

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
    private boolean isDoubleValue(String value, String valueName) {
        String doubleValue = "([-+]?\\d*[.,]?\\d*)";
        Pattern doubleValuePattern = Pattern.compile(doubleValue);
        if (doubleValuePattern.matcher(value).matches())
            return true;
        FileHandler.writeUserErrors(String.format("%s coordinate must be of type double", valueName));
        Console.printUserErrorsFile();
        FileHandler.clearUserErrFile();
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
            for (int i = 0; i < ID_LENGTH; i++) {
                newId.append((long) (Math.random() * 10));
            }
        } while (hasElementWithId(Long.parseLong(newId.toString())));
        return Long.parseLong(newId.toString());
    }

    public boolean checkName(String name) {
        //Поле не может быть null, Строка не может быть пустой
        if (name == null || name == "") {
            FileHandler.writeUserErrors("Name cannot be null");

            Console.printUserErrorsFile();
            FileHandler.clearUserErrFile();
            return false;
        }
        return true;
    }
    public boolean checkX(String newX) {
        if (!isDoubleValue(newX, "X"))
            return false;
        float x = Float.parseFloat(newX);
        if (x > 341) {
            FileHandler.writeUserErrors("");
            Console.printUserErrorsFile();
            FileHandler.clearUserErrFile();
            return false;
        }
        return true;
    }
    public boolean checkY(String newY) {
        if (!isDoubleValue(newY, "Y"))
            return false;
        double y = Double.parseDouble(newY);
        if (y <= -272) {
            FileHandler.writeUserErrors("");
            Console.printUserErrorsFile();
            FileHandler.clearUserErrFile();
            return false;
        }
        return true;
    }

    public boolean checkEnginePower(String newEnginePower) {
        //Значение поля должно быть больше 0
        //проверка регулярками

        int enginePower = Integer.parseInt(newEnginePower);
        if (enginePower <= 0) {
            FileHandler.writeUserErrors("");
            Console.printUserErrorsFile();
            FileHandler.clearUserErrFile();
            return false;
        }
        return true;
    }
    public boolean checkDistanceTravelled(String newDistanceTravelled) {
        //Значение поля должно быть больше 0
        long distanceTravelled = Long.parseLong(newDistanceTravelled);
        if (distanceTravelled <= 0) {
            FileHandler.writeUserErrors("");
            Console.printUserErrorsFile();
            FileHandler.clearUserErrFile();
            return false;
        }
        return true;
    }
    public boolean checkVehicleType(String newVehicleType) {
        //Поле не может быть null
        //проверка регулярками

        if (newVehicleType == "") {
            FileHandler.writeUserErrors("");
            Console.printUserErrorsFile();
            FileHandler.clearUserErrFile();
            return false;
        }
        int vehicleType = Integer.parseInt(newVehicleType);
        //выбор значения из enum
        return true;
    }
    public boolean checkFuelType(String newFuelType) {
        //Поле не может быть null
        //проверка регулярками

        if (newFuelType == "") {
            FileHandler.writeUserErrors("");
            Console.printUserErrorsFile();
            FileHandler.clearUserErrFile();
            return false;
        }
        int fuelType = Integer.parseInt(newFuelType);
        //проверка существования значения в enum
        return true;
    }

}
