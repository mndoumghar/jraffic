package simulation;

import model.Direction;
import model.Vehicle;
import utils.Constants;
import java.util.List;

public class CollisionManager {

    public boolean isBlocked(Vehicle v, List<Vehicle> vehicles) {
        for (Vehicle other : vehicles) {
            if (v == other) continue;

            if (v.getDirection() == other.getDirection()) {
                double distance = 0;
                boolean isAhead = false;

                switch (v.getDirection()) {
                    case NORTH -> {
                        if (Math.abs(v.getX() - other.getX()) < Constants.VEHICLE_WIDTH) {
                            if (other.getY() < v.getY()) {
                                distance = v.getY() - other.getY();
                                isAhead = true;
                            }
                        }
                    }
                    case SOUTH -> {
                        if (Math.abs(v.getX() - other.getX()) < Constants.VEHICLE_WIDTH) {
                            if (other.getY() > v.getY()) {
                                distance = other.getY() - v.getY();
                                isAhead = true;
                            }
                        }
                    }
                    case EAST -> {
                        if (Math.abs(v.getY() - other.getY()) < Constants.VEHICLE_WIDTH) {
                            if (other.getX() > v.getX()) {
                                distance = other.getX() - v.getX();
                                isAhead = true;
                            }
                        }
                    }
                    case WEST -> {
                        if (Math.abs(v.getY() - other.getY()) < Constants.VEHICLE_WIDTH) {
                            if (other.getX() < v.getX()) {
                                distance = v.getX() - other.getX();
                                isAhead = true;
                            }
                        }
                    }
                }

                if (isAhead && distance < (Constants.VEHICLE_LENGTH + Constants.SAFETY_GAP)) {
                    return true;
                }
            }
            
            if (v.getDirection() != other.getDirection()) {
                if (isInIntersection(other) && isApproachingOrInIntersection(v, other)) {
                     if (boundingBoxIntersect(v, other)) {
                         return true;
                     }
                }
            }
        }
        return false;
    }
    
    private boolean isInIntersection(Vehicle v) {
        double minX = Constants.CENTER_X - Constants.ROAD_WIDTH / 2.0;
        double maxX = Constants.CENTER_X + Constants.ROAD_WIDTH / 2.0;
        double minY = Constants.CENTER_Y - Constants.ROAD_WIDTH / 2.0;
        double maxY = Constants.CENTER_Y + Constants.ROAD_WIDTH / 2.0;
        
        return v.getX() + Constants.VEHICLE_WIDTH > minX && v.getX() < maxX &&
               v.getY() + Constants.VEHICLE_LENGTH > minY && v.getY() < maxY;
    }
    
    private boolean isApproachingOrInIntersection(Vehicle v, Vehicle other) {
         double nextX = v.getX();
         double nextY = v.getY();
         
         switch (v.getDirection()) {
            case NORTH -> nextY -= Constants.VEHICLE_SPEED * 2;
            case SOUTH -> nextY += Constants.VEHICLE_SPEED * 2;
            case EAST  -> nextX += Constants.VEHICLE_SPEED * 2;
            case WEST  -> nextX -= Constants.VEHICLE_SPEED * 2;
         }
         
         return (nextX < other.getX() + Constants.VEHICLE_WIDTH && 
                 nextX + Constants.VEHICLE_WIDTH > other.getX() &&
                 nextY < other.getY() + Constants.VEHICLE_LENGTH && 
                 nextY + Constants.VEHICLE_LENGTH > other.getY());
    }
    
    private boolean boundingBoxIntersect(Vehicle v, Vehicle other) {
        double safeX = Constants.SAFETY_GAP / 2.0;
        double safeY = Constants.SAFETY_GAP / 2.0;
        
        return (v.getX() - safeX < other.getX() + Constants.VEHICLE_WIDTH + safeX && 
                v.getX() + Constants.VEHICLE_WIDTH + safeX > other.getX() - safeX &&
                v.getY() - safeY < other.getY() + Constants.VEHICLE_LENGTH + safeY && 
                v.getY() + Constants.VEHICLE_LENGTH + safeY > other.getY() - safeY);
    }
}
