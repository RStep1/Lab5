package util;

import data.Coordinates;
import data.FuelType;
import data.Vehicle;
import data.VehicleType;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.format.DateTimeFormatter;
import java.util.function.BiFunction;
import java.util.function.Function;

public class ValueOperator {
    private static final String zonedDatePattern = "dd/MM/yyy - HH:mm:ss z";
    private static final DateTimeFormatter zonedDateFormatter = DateTimeFormatter.ofPattern(zonedDatePattern);

    public static final Function<String, String> VALUE_CORRECTION =
            value -> value.replaceAll(",", ".").replaceAll("\\+", "");
    private static final BiFunction<String, String, Coordinates> SET_COORDINATES = (newX, newY) -> {
        float x = Float.parseFloat(newX);
        double y = Double.parseDouble(newY);
        float truncatedX = BigDecimal.valueOf(x).setScale(Coordinates.getAccuracy(),
                RoundingMode.HALF_UP).floatValue();
        double truncatedY = BigDecimal.valueOf(y).setScale(Coordinates.getAccuracy(),
                RoundingMode.HALF_UP).doubleValue();
        return new Coordinates(truncatedX, truncatedY);
    };
    private static final Function<java.time.ZonedDateTime, String> SET_CREATION_DATE = (creationDateTime) ->
            creationDateTime.format(zonedDateFormatter);
    private static final Function<String, Integer> SET_ENGINE_POWER = Integer::parseInt;
    private static final Function<String, Long> SET_DISTANCE_TRAVELLED = Long::parseLong;
    private static final Function<String, VehicleType> SET_VEHICLE_TYPE = (newType) -> {
        VehicleType type = VehicleType.CAR;
        try {
            int serialNumber = Integer.parseInt(newType);
            for (VehicleType vehicleType : VehicleType.values())
                if (vehicleType.getSerialNumber() == serialNumber)
                    type = vehicleType;
        } catch (NumberFormatException e) {
            type = VehicleType.valueOf(newType);
        }
        return type;
    };
    private static final Function<String, FuelType> SET_FUEL_TYPE = (newFuelType) -> {
        FuelType fuelType = FuelType.ALCOHOL;
        try {
            int serialNumber = Integer.parseInt(newFuelType);
            for (FuelType fuelType1 : FuelType.values())
                if (fuelType1.getSerialNumber() == serialNumber)
                    fuelType = fuelType1;
        } catch (NumberFormatException e) {
            fuelType = FuelType.valueOf(newFuelType);
        }
        return fuelType;
    };

    public static final Vehicle setVehicle(long id, String newName, String newX, String newY,
                                           java.time.ZonedDateTime creationDate, String newEnginePower,
                                           String newDistanceTravelled, String newVehicleType,
                                           String newFuelType) {
        return new Vehicle(id, newName, SET_COORDINATES.apply(newX, newY),
                SET_CREATION_DATE.apply(creationDate), SET_ENGINE_POWER.apply(newEnginePower),
                SET_DISTANCE_TRAVELLED.apply(newDistanceTravelled), SET_VEHICLE_TYPE.apply(newVehicleType),
                SET_FUEL_TYPE.apply(newFuelType));
    }

}
