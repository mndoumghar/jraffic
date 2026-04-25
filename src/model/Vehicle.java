package model;

import traffic.TrafficController;
import utils.Constants;

public class Vehicle {
    private static final double CENTER = 400;
private static final double LANE_OFFSET = 20;
private static final double STOP_OFFSET = 40;

    private double x;
    private double y;
    private Direction direction;
    private boolean stopped = false;
     private long lastSwitchTime = 0;
    private boolean paused =false;
    
   

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

    public void stop() {
        stopped = true;
    }

    public void go() {
        stopped = false;
    }

    public double getX() { return x; }
    public double getY() { return y; }

 
    public void update(TrafficController controller, long now) {

    boolean atStopLine = isAtStopLine();

    boolean canMove = true;

    switch (direction) {

        case NORTH, SOUTH -> {
            if (atStopLine && !controller.getNorthSouthLight().isGreen()) {
                canMove = false;
            }
        }

        case EAST, WEST -> {
            if (atStopLine && !controller.getEastWestLight().isGreen()) {
                canMove = false;
            }
        }
    }

    stopped = !canMove;

    if (!stopped) {
        move();
    }
}
private boolean isAtStopLine() {

    switch (direction) {

        case NORTH:
            return y <= CENTER + STOP_OFFSET;

        case SOUTH:
            return y >= CENTER - STOP_OFFSET;

        case EAST:
            return x >= CENTER - STOP_OFFSET;

        case WEST:
            return x <= CENTER + STOP_OFFSET;
    }

    return false;
}

}
