package processing;

import data.Vehicle;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class CollectionHandler {
    private final Hashtable<Long, Vehicle> dataBase;
    private static final int NUMBER_OF_ID_DIGITS = 6;

    public CollectionHandler(Hashtable<Long, Vehicle> dataBase) {
        this.dataBase = dataBase;
    }

    public boolean checkId(String strId) {
        //только цифры, > 0, 6-и значное число
        String correctId = String.format("\\d{%s}", NUMBER_OF_ID_DIGITS);
        Pattern pattern = Pattern.compile(correctId);
//        Matcher matcher = pattern.matcher(strId);

        //incorrect length
        //incorrect symbols
        //negative

        if (pattern.matcher(strId).matches()) {

        }

        Long newId = new Long(1);
        Enumeration<Long> ids = dataBase.keys();
        while (ids.hasMoreElements()) {
            Long id = ids.nextElement();
            if (!newId.equals(id))
                return false;
        }
        return true;
    }

    public int generateId() {
        Random random = new Random();
        random.nextLong();
        return 1;
    }
}
