package processing;

import data.Vehicle;
import exceptions.NoSuchIdException;

import java.util.Enumeration;
import java.util.Hashtable;

public class IdentifierHandler {
    Hashtable<Long, Vehicle> dataBase;

    public IdentifierHandler(Hashtable<Long, Vehicle> dataBase) {
        this.dataBase = dataBase;
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
