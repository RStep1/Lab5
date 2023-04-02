package processing;

import data.Vehicle;
import exceptions.NoSuchIdException;
import mods.FileType;

import java.io.File;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.regex.Pattern;

public class IdentifierHandler {
    Hashtable<Long, Vehicle> dataBase;
    private static final int ID_LENGTH = 10;
    private static final int MAX_KEY_LENGTH = 10;

    public IdentifierHandler(Hashtable<Long, Vehicle> dataBase) {
        this.dataBase = dataBase;
    }

    private boolean hasNonNumericCharacters(String value, String valueName, String commandName) {
        String nonDigitValue = "-?\\d+";
        Pattern nonDigitValuePattern = Pattern.compile(nonDigitValue);
        if (nonDigitValuePattern.matcher(value).matches()) {
            return false;
        }
        FileHandler.writeCurrentCommand(commandName, FileType.USER_ERRORS);
        FileHandler.writeUserErrors(String.format("%s must be a number", valueName));
        return true;
    }
    private boolean isNegativeValue(String value, String valueName, String commandName) {
        String nonPositiveValue = "-\\d+";
        Pattern nonPositiveValuePattern = Pattern.compile(nonPositiveValue);
        if (nonPositiveValuePattern.matcher(value).matches()) {
            FileHandler.writeCurrentCommand(commandName, FileType.USER_ERRORS);
            FileHandler.writeUserErrors(String.format("%s cannot be negative", valueName));
            return true;
        }
        return false;
    }
    private boolean hasLeadingZeros(String value, String valueName, String commandName) {
        String leadingZeros = "^0+\\d+";
        Pattern leadingZerosPattern = Pattern.compile(leadingZeros);
        if (leadingZerosPattern.matcher(value).matches()) {
            FileHandler.writeCurrentCommand(commandName, FileType.USER_ERRORS);
            FileHandler.writeUserErrors(String.format("%s cannot have leading zeros", valueName));
            return true;
        }
        return false;
    }

    public boolean checkKey(String key, String commandName) {
        if (hasNonNumericCharacters(key, "Key", commandName))
            return false;
        if (isNegativeValue(key, "Key", commandName))
            return false;
        if (hasLeadingZeros(key, "key", commandName))
            return false;
        if (key.length() > MAX_KEY_LENGTH) {
            FileHandler.writeCurrentCommand(commandName, FileType.USER_ERRORS);
            FileHandler.writeUserErrors(String.format("Key is too long, max length - %s", MAX_KEY_LENGTH));
            return false;
        }
        return true;
    }


    public boolean checkId(String id, String commandName) {
        if (hasNonNumericCharacters(id, "Id", commandName))
            return false;
        if (isNegativeValue(id, "Id", commandName))
            return false;
        if (hasLeadingZeros(id, "Id", commandName))
            return false;
        if (id.length() != ID_LENGTH) {
            FileHandler.writeCurrentCommand(commandName, FileType.USER_ERRORS);
            FileHandler.writeUserErrors(String.format("Invalid id length: %s, expected %s",
                    id.length(), ID_LENGTH));
            return false;
        }
        if (!hasElementWithId(Long.parseLong(id))) {
            FileHandler.writeCurrentCommand(commandName, FileType.USER_ERRORS);
            FileHandler.writeUserErrors("No such element with this id");
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

    public boolean hasElementWithKey(String key, boolean expectedResult, String commandName) {
        if (dataBase.containsKey(Long.parseLong(key))) {
            if (expectedResult) {
                FileHandler.writeCurrentCommand(commandName, FileType.USER_ERRORS);
                FileHandler.writeUserErrors("Element with such key already exists");
            }
            return true;
        }
        if (!expectedResult) {
            FileHandler.writeCurrentCommand(commandName, FileType.USER_ERRORS);
            FileHandler.writeUserErrors("Element with such key not found");
        }
        return false;
    }

    public Long generateId() {
        StringBuilder newId;
        do {
            newId = new StringBuilder(ID_LENGTH);
            newId.append((byte) (Math.random() * 9 + 1)); //skip leading zero
            for (int i = 1; i < ID_LENGTH; i++) {
                newId.append((byte) (Math.random() * 10));
            }
        } while (hasElementWithId(Long.parseLong(newId.toString())));
        return Long.parseLong(newId.toString());
    }

    public long getKeyById(long id) {
        long key = -1;
        Enumeration<Long> keys = dataBase.keys();
        while (keys.hasMoreElements()) {
            Long nextKey = keys.nextElement();
            if (id == dataBase.get(nextKey).getId()) {
                key = nextKey;
            }
        }
        if (key == -1) {
            RuntimeException e = new NoSuchIdException(id);
            FileHandler.writeSystemErrors(e.getMessage());
            throw e;
        }
        return key;
    }
}
