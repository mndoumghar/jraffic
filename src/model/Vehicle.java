package model;

import traffic.TrafficController;
import utils.Constants;

public class Vehicle {

    private double x;
    private double y;
    private Direction direction;
    private boolean stopped = false;

    public Vehicle(double x, double y, Direction direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public void move() {
        if (stopped) return;

        switch (direction) {
            case NORTH -> y -= Constants.VEHICLE_SPEED;
            case SOUTH -> y += Constants.VEHICLE_SPEED;
            case EAST  -> x += Constants.VEHICLE_SPEED;
            case WEST  -> x -= Constants.VEHICLE_SPEED;
        }
    }

    public double getX() { return x; }
    public double getY() { return y; }
    public Direction getDirection() { return direction; }

    public void update(TrafficController controller, long now, boolean collisionStop) {
        boolean atStopLine = isAtStopLine();
        boolean trafficStop = false;

        switch (direction) {
            case NORTH, SOUTH -> {
                if (atStopLine && !controller.getNorthSouthLight().isGreen()) {
                    trafficStop = true;
                }
            }
            case EAST, WEST -> {
                if (atStopLine && !controller.getEastWestLight().isGreen()) {
                    trafficStop = true;
                }
            }
        }

        stopped = trafficStop || collisionStop;

        if (!stopped) {
            move();
        }
    }

    private boolean isAtStopLine() {
        
        double buffer = Constants.VEHICLE_SPEED * 2;
        
        switch (direction) {
            case NORTH: {
                double stopLineY = Constants.CENTER_Y + Constants.STOP_LINE_OFFSET;
                return y <= stopLineY && y > stopLineY - buffer;
            }
            case SOUTH: {
                double frontY = y + Constants.VEHICLE_LENGTH;
                double stopLineY = Constants.CENTER_Y - Constants.STOP_LINE_OFFSET;
                return frontY >= stopLineY && frontY < stopLineY + buffer;
            }
            case EAST: {
                double frontX = x + Constants.VEHICLE_LENGTH;
                double stopLineX = Constants.CENTER_X - Constants.STOP_LINE_OFFSET;
                return frontX >= stopLineX && frontX < stopLineX + buffer;
            }
            case WEST: {
                double stopLineX = Constants.CENTER_X + Constants.STOP_LINE_OFFSET;
                return x <= stopLineX && x > stopLineX - buffer;
            }
        }

        return false;
    }
}
