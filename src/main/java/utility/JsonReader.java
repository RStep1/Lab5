package utility;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import data.Vehicle;


import java.util.Hashtable;



public class JsonReader {
    private final String json;
    public JsonReader(String json) {
        this.json = json;
    }

    public Hashtable<Long, Vehicle> readDataBase() {
        Gson gson = new Gson();
        Hashtable<Long, Vehicle> vehicleHashtable
                = gson.fromJson(json, new TypeToken<Hashtable<Long, Vehicle>>(){}.getType());
        return vehicleHashtable;
    }
}
