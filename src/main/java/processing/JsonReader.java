package processing;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import data.Vehicle;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;


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


        /*Hashtable<Long, Vehicle> vehicleHashtable = new Hashtable<>();
        ObjectMapper mapper = new ObjectMapper();
        List<Vehicle> methodList = new ArrayList<>();
        try {
            methodList = Arrays.asList(mapper.readValue(json, Vehicle[].class));
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (Vehicle vehicle : methodList) {
            vehicleHashtable.put(vehicle.getId(), vehicle);
        }
        return vehicleHashtable;*/
    }
}
