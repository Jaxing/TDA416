package Lab2;

/**
 * Created by jesper on 2016-02-04.
 */
public class Point {
    double x, y;

    public Point() {
        x = y = 0;
    }

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double distanceTo(Point point) {
        double dX = Math.abs(this.x - point.x);
        double dY = Math.abs(this.y - point.y);

        return Math.sqrt(Math.pow(dX, 2) + Math.pow(dY, 2));
    }
}
