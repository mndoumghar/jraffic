package simulation;

import model.Direction;
import model.Vehicle;
import traffic.TrafficController;

import java.util.ArrayList;
import java.util.List;

public class SimulationEngine {

    private static final int    W            = 900;
    private static final int    H            = 800;
    private static final double LANE_LENGTH  = 400.0;
    private static final double VEHICLE_SIZE = 30.0;
    private static final double SAFETY_GAP   = 50.0;
    private static final int    CAPACITY     = (int) Math.floor(LANE_LENGTH / (VEHICLE_SIZE + SAFETY_GAP));

    private final List<Vehicle>       vehicles;
    private final TrafficController   controller;
    private final CollisionManager    collisionManager;
    private final SpawnManager        spawnManager;

    private long lastNano = 0;

    public SimulationEngine(TrafficController controller) {
        this.controller       = controller;
        this.vehicles         = new ArrayList<>();
        this.collisionManager = new CollisionManager();
        this.spawnManager     = new SpawnManager();
    }

    public void addVehicle(Vehicle v) {
        vehicles.add(v);
    }

    public void update(long nowNano) {
        if (lastNano == 0) { lastNano = nowNano; return; }
        double dt = (nowNano - lastNano) / 1_000_000_000.0;
        lastNano = nowNano;

        int[] counts = laneCounts();
        controller.update(dt, counts[0], counts[1], counts[2], counts[3], CAPACITY);

        for (int i = 0; i < vehicles.size(); i++) {
            Vehicle v = vehicles.get(i);
            boolean blocked = collisionManager.isBlocked(v, vehicles);
            v.update(dt, controller.state, blocked, W, H);
        }

        vehicles.removeIf(v -> v.getX() < -30 || v.getX() > W + 30
                            || v.getY() < -30 || v.getY() > H + 30);
    }

    private int[] laneCounts() {
        int north = 0, south = 0, east = 0, west = 0;
        for (Vehicle v : vehicles) {
            switch (v.getDirection()) {
                case NORTH: north++; break;
                case SOUTH: south++; break;
                case EAST:  east++;  break;
                case WEST:  west++;  break;
            }
        }
        return new int[]{ north, south, east, west };
    }

    public List<Vehicle>     getVehicles()   { return vehicles; }
    public TrafficController getController() { return controller; }
    public SpawnManager      getSpawnManager() { return spawnManager; }
}