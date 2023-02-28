package data;

public enum FuelType {
    ALCOHOL(1),
    MANPOWER(2),
    NUCLEAR(3);
    private final int serialNumber;
    FuelType(int serialNumber) {
        this.serialNumber = serialNumber;
    }
    public int getSerialNumber() {
        return serialNumber;
    }
}
