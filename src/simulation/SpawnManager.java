package simulation;

import model.Direction;
import model.Vehicle;
import java.util.Random;

public class SpawnManager {

    private static final int    W          = 900;
    private static final int    H          = 800;
    private static final double SAFE_DIST  = 60.0;

    private final Random random = new Random();

    public void trySpawn(SimulationEngine engine, Direction dir) {
        double[] pos = spawnPosition(dir);
        if (canSpawn(engine, dir, pos[0], pos[1])) {
            engine.addVehicle(new Vehicle(pos[0], pos[1], dir));
        }
    }

    public void trySpawnRandom(SimulationEngine engine) {
        Direction[] dirs = Direction.values();
        trySpawn(engine, dirs[random.nextInt(dirs.length)]);
    }

    private double[] spawnPosition(Direction dir) {
        double cx = W / 2.0;
        double cy = H / 2.0;
        switch (dir) {
            case NORTH: return new double[]{ cx + 15, H - 35 };
            case SOUTH: return new double[]{ cx - 45, 10      };
            case WEST:  return new double[]{ W - 35,  cy - 45 };
            case EAST:  return new double[]{ 10,      cy + 15 };
            default:    return new double[]{ 0, 0 };
        }
    }

    private boolean canSpawn(SimulationEngine engine, Direction dir, double x, double y) {
        for (Vehicle v : engine.getVehicles()) {
            if (v.getDirection() == dir && Math.hypot(v.getX() - x, v.getY() - y) < SAFE_DIST) {
                return false;
            }
        }
        return true;
    }
}
