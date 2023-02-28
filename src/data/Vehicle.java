package data;

public class Vehicle {
    private long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private int enginePower; //Значение поля должно быть больше 0
    private long distanceTravelled; //Значение поля должно быть больше 0
    private VehicleType type; //Поле не может быть null
    private FuelType fuelType; //Поле не может быть null


    public Vehicle(

            public Vehicle() {
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
    public java.time.ZonedDateTime getCreationDate() {
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
}
