package processing;

import data.Vehicle;

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

    public static boolean hasElementWithId(String id) {

        return false;
    }


    public static String generateId() {
        StringBuilder newId = new StringBuilder(NUMBER_OF_ID_DIGITS);
        do {
            for (int i = 0; i < NUMBER_OF_ID_DIGITS; i++) {
                newId.append((long) (Math.random() * 10));
            }
        } while (hasElementWithId(newId.toString()));
        return newId.toString();
    }
}
