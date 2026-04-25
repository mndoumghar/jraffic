package simulation;
import model.Vehicle;
import java.util.List;

public class CollisionManager {

    private static final double SAFETY_DIST = 50.0;

    public boolean isBlocked(Vehicle subject, List<Vehicle> all) {
        for (Vehicle other : all) {
            if (other == subject) continue;
            if (other.getDirection() != subject.getDirection()) continue;

            double dist = Math.hypot(subject.getX() - other.getX(),
                                     subject.getY() - other.getY());
            if (dist < SAFETY_DIST && isAhead(subject, other)) return true;
        }
        return false;
    }

    private boolean isAhead(Vehicle subject, Vehicle other) {
        switch (subject.getDirection()) {
            case NORTH: return other.getY() < subject.getY();
            case SOUTH: return other.getY() > subject.getY();
            case WEST:  return other.getX() < subject.getX();
            case EAST:  return other.getX() > subject.getX();
            default:    return false;
        }
    }
}
