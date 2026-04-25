package simulation;

import model.Vehicle;
import traffic.TrafficController;

import java.util.ArrayList;
import java.util.List;

public class SimulationEngine {

    private List<Vehicle> vehicles = new ArrayList<>();
    private TrafficController controller;

    public SimulationEngine(TrafficController controller) {
        this.controller = controller;
    }

    public void addVehicle(Vehicle v) {
        vehicles.add(v);
    }

    public void update(long now) {

        controller.update(now);

        for (Vehicle v : vehicles) {
            v.update(controller, now);
        }
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public TrafficController getController() {
        return controller;
    }
}