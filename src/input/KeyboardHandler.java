package input;

import javafx.scene.Scene;
import model.Direction;
import model.Vehicle;
import simulation.SimulationEngine;

import java.util.Random;

public class KeyboardHandler {

    private SimulationEngine engine;
    private Random random = new Random();

    public KeyboardHandler(Scene scene, SimulationEngine engine) {

        this.engine = engine;

        scene.setOnKeyPressed(event -> {

            switch (event.getCode()) {

                case UP ->
                        engine.addVehicle(new Vehicle(400, 800, Direction.NORTH));

                case DOWN ->
                        engine.addVehicle(new Vehicle(360, 0, Direction.SOUTH));

                case LEFT ->
                        engine.addVehicle(new Vehicle(800, 360, Direction.WEST));

                case RIGHT ->
                        engine.addVehicle(new Vehicle(0, 400, Direction.EAST));

                case R -> {
                    Direction d = Direction.values()[random.nextInt(4)];

        switch (d) {
            case NORTH -> engine.addVehicle(new Vehicle(400, 800, d));
            case SOUTH -> engine.addVehicle(new Vehicle(400, 0, d));
            case EAST  -> engine.addVehicle(new Vehicle(0, 400, d));
            case WEST  -> engine.addVehicle(new Vehicle(800, 400, d));
        }
    }
            }
        });
    }
}