package data;

public class Coordinates {
    private float x; //Максимальное значение поля: 341
    private double y; //Значение поля должно быть больше -272

    public Coordinates(float x, double y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }
    public double getY() {
        return y;
    }

    public String toString() {
        return String.format("(%.6f; %.6f)", x, y);
    }
}
