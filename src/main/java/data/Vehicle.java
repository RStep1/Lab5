package data;

public class Vehicle {
    private long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private String creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private int enginePower; //Значение поля должно быть больше 0
    private long distanceTravelled; //Значение поля должно быть больше 0
    private VehicleType type; //Поле не может быть null
    private FuelType fuelType; //Поле не может быть null

    private Vehicle() {

    }
    public Vehicle(long id, String name, Coordinates coordinates,
                   String creationDate, int enginePower,
                   long distanceTravelled, VehicleType type, FuelType fuelType) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.enginePower = enginePower;
        this.distanceTravelled = distanceTravelled;
        this.type = type;
        this.fuelType = fuelType;
    }

    public long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public Coordinates getCoordinates() {
        return coordinates;
    }
    public String getCreationDate() {
        return creationDate;
    }
    public int getEnginePower() {
        return enginePower;
    }
    public long getDistanceTravelled() {
        return distanceTravelled;
    }
    public VehicleType getType() {
        return type;
    }
    public FuelType getFuelType() {
        return fuelType;
    }

    @Override
    public String toString() {
        return String.format("id:                 %s" +
                             "\nname:               %s" +
                             "\ncoodrinates:        %s" +
                             "\ncreation date:      %s" +
                             "\nengine power:       %s" +
                             "\ndistance travelled: %s" +
                             "\nvehicle type:       %s" +
                             "\nfuel type:          %s" +
                             "\n____________________________________________________________",
                id, name, coordinates, creationDate,
                enginePower, distanceTravelled, type, fuelType);
    }
}
