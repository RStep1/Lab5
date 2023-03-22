package data;

import java.util.Comparator;

public class Coordinates implements Comparator<Coordinates> {
    private float x; //Максимальное значение поля: 341
    private double y; //Значение поля должно быть больше -272
    private static final int accuracy = 5;

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

    public static int getAccuracy() {
        return accuracy;
    }

    @Override
    public String toString() {
        return String.format("(%." + accuracy + "f; %." + accuracy + "f)", x, y);
    }

    @Override
    public int compare(Coordinates o1, Coordinates o2) {
        Comparator<Coordinates> xComparator = (o11, o21) -> Float.compare(o11.getX(), o21.getX());
        Comparator<Coordinates> yComparator = Comparator.comparingDouble(Coordinates::getY);
        return xComparator.thenComparing(yComparator).compare(o1, o2);
    }
}
