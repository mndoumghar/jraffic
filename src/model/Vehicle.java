package model;

import javafx.scene.paint.Color;
import java.util.Random;

public class Vehicle {

    private static final double   SPEED = 300.0;
    private static final Random   RAND  = new Random();

    private final Direction direction;
    private final Color     color;
    private double x;
    private double y;

    public Vehicle(double x, double y, Direction direction) {
        this.direction = direction;
        this.x         = x;
        this.y         = y;
        this.color     = pickColor();
    }

    private static Color pickColor() {
        switch (RAND.nextInt(3)) {
            case 0:  return Color.YELLOW;
            case 1:  return Color.BLUE;
            default: return Color.RED;
        }
    }

    public void update(double dt, Direction green, boolean blocked, int w, int h) {
        if (blocked) return;

        final double cx   = w / 2.0;
        final double cy   = h / 2.0;
        final double step = SPEED * dt;

        switch (direction) {
            case NORTH:
                if (color == Color.RED    && y >= cy + 12 && y <= cy + 18) { y = cy + 15; x += step; }
                else if (color == Color.YELLOW && y >= cy - 48 && y <= cy - 43) { y = cy - 45; x -= step; }
                else if (green == direction)   { y -= step; }
                else if (y >= cy + 65)         { y = Math.max(y - step, cy + 65); }
                else if (y <  cy + 60)         { y -= step; }
                break;

            case SOUTH:
                if (color == Color.RED    && y >= cy - 48 && y <= cy - 43) { y = cy - 45; x -= step; }
                else if (color == Color.YELLOW && y >= cy + 12 && y <= cy + 18) { y = cy + 15; x += step; }
                else if (green == direction)   { y += step; }
                else if (y <= cy - 95)         { y = Math.min(y + step, cy - 95); }
                else if (y >  cy - 90)         { y += step; }
                break;

            case WEST:
                if (color == Color.RED    && x >= cx + 12 && x <= cx + 18) { x = cx + 15; y -= step; }
                else if (color == Color.YELLOW && x >= cx - 48 && x <= cx - 42) { x = cx - 45; y += step; }
                else if (green == direction)   { x -= step; }
                else if (x >= cx + 65)         { x = Math.max(x - step, cx + 65); }
                else if (x <  cx + 60)         { x -= step; }
                break;

            case EAST:
                if (color == Color.RED    && x >= cx - 48 && x <= cx - 42) { x = cx - 45; y += step; }
                else if (color == Color.YELLOW && x >= cx + 12 && x <= cx + 18) { x = cx + 15; y -= step; }
                else if (green == direction)   { x += step; }
                else if (x <= cx - 95)         { x = Math.min(x + step, cx - 95); }
                else if (x >  cx - 90)         { x += step; }
                break;
        }
    }

    public double    getX()         { return x; }
    public double    getY()         { return y; }
    public Direction getDirection() { return direction; }
    public Color     getColor()     { return color; }
}
