package processing;

import data.Coordinates;
import data.FuelType;
import data.Vehicle;
import data.VehicleType;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class CollectionHandler {
    private final Hashtable<Long, Vehicle> dataBase;
    private static final int NUMBER_OF_ID_DIGITS = 10;

    public CollectionHandler(Hashtable<Long, Vehicle> dataBase) {
        this.dataBase = dataBase;
    }

    public boolean checkKey(Long key) {

        return true;
    }

    private boolean hasElementWithKey(Long key) {

        return true;
    }

    public boolean checkId(String strId) {
        String correctId = String.format("\\d{%s}", NUMBER_OF_ID_DIGITS);
        String incorrectLengthId = "\\d+";
        String nonPositiveId = String.format("-\\d{$s}", NUMBER_OF_ID_DIGITS);
        Pattern correctIdPattern = Pattern.compile(correctId);
        Pattern incorrectLengthIdPattern = Pattern.compile(incorrectLengthId);
        Pattern nonPositiveIdPattern = Pattern.compile(nonPositiveId);

        if (correctIdPattern.matcher(strId).matches()) {
            if (!dataBase.containsKey(Long.parseLong(strId)))
                return true;
            FileHandler.writeUserErrors("Object with this id already exist");
            return false;
        }
        if (incorrectLengthIdPattern.matcher(strId).matches()) {
            FileHandler.writeUserErrors("### incorrect length of id ###");
            return false;
        }
        if (nonPositiveIdPattern.matcher(strId).matches()) {
            FileHandler.writeUserErrors("###non-positive id###");
            return false;
        }
        FileHandler.writeUserErrors("###id contains not numeric value###");
        return false;
    }

    public boolean hasElementWithId(String id) {

        return false;
    }


    public String generateId() {
        StringBuilder newId = new StringBuilder(NUMBER_OF_ID_DIGITS);
        do {
            for (int i = 0; i < NUMBER_OF_ID_DIGITS; i++) {
                newId.append((long) (Math.random() * 10));
            }
        } while (hasElementWithId(newId.toString()));
        return newId.toString();
    }

    public boolean checkName(String name) {
        //Поле не может быть null, Строка не может быть пустой
        if (name == null || name == "")
            return false;
        return true;
    }
    public boolean checkCoordinates(Coordinates coordinates) {
        //Поле не может быть null
        if (coordinates == null || coordinates.getX() > 341 || coordinates.getY() <= -272)
            return false;
        //x - Максимальное значение поля: 341
        //y - Значение поля должно быть больше -272
        return true;
    }
    public boolean checkEnginePower(int enginePower) {
        //Значение поля должно быть больше 0
        if (enginePower <= 0) {

            return false;
        }
        return true;
    }
    public boolean checkDistanceTravelled(long distanceTravelled) {
        //Значение поля должно быть больше 0
        if (distanceTravelled <= 0)
            return false;
        return true;
    }
    public boolean checkVehicleType(VehicleType vehicleType) {
        //Поле не может быть null
        if (vehicleType == null)
            return false;
        return true;
    }
    public boolean checkFuelType(FuelType fuelType) {
        //Поле не может быть null
        if (fuelType == null)
            return false;
        return true;
    }

}
