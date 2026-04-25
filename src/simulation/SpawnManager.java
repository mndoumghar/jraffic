package simulation;

import model.Direction;
import model.Vehicle;
import utils.Constants;

import java.util.Random;

public class SpawnManager {

    private long lastSpawnTime = 0;
    private Random random = new Random();

    public void trySpawn(SimulationEngine engine, Direction dir) {
        long now = System.currentTimeMillis();
        if (now - lastSpawnTime >= Constants.SPAWN_COOLDOWN_MS) {
            spawnVehicle(engine, dir);
            lastSpawnTime = now;
        }
    }

    public void trySpawnRandom(SimulationEngine engine) {
        Direction[] dirs = Direction.values();
        Direction dir = dirs[random.nextInt(dirs.length)];
        trySpawn(engine, dir);
    }

    private void spawnVehicle(SimulationEngine engine, Direction dir) {
        double x = 0;
        double y = 0;
        
        double laneOffset = Constants.ROAD_WIDTH / 4.0;
        double halfCar = Constants.VEHICLE_WIDTH / 2.0;

        switch (dir) {
            case NORTH -> {
                x = Constants.CENTER_X + laneOffset - halfCar;
                y = Constants.WINDOW_HEIGHT + Constants.VEHICLE_LENGTH;
            }
            case SOUTH -> {
                x = Constants.CENTER_X - laneOffset - halfCar;
                y = -Constants.VEHICLE_LENGTH;
            }
            case EAST -> {
                y = Constants.CENTER_Y + laneOffset - halfCar;
                x = -Constants.VEHICLE_LENGTH;
            }
            case WEST -> {
                y = Constants.CENTER_Y - laneOffset - halfCar;
                x = Constants.WINDOW_WIDTH + Constants.VEHICLE_LENGTH;
            }
        }

        for (Vehicle v : engine.getVehicles()) {
            if (v.getDirection() == dir) {
                if (Math.abs(v.getX() - x) < Constants.VEHICLE_WIDTH && 
                    Math.abs(v.getY() - y) < (Constants.VEHICLE_LENGTH + Constants.SAFETY_GAP)) {
                    return;
                }
            }
        }

        engine.addVehicle(new Vehicle(x, y, dir));
    }
}
