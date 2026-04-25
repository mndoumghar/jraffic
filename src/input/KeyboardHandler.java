package input;

import javafx.scene.Scene;
import model.Direction;

import simulation.SimulationEngine;
import simulation.SpawnManager;

public class KeyboardHandler {

    public KeyboardHandler(Scene scene, SimulationEngine engine) {
        SpawnManager spawner = engine.getSpawnManager();
        
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case UP -> spawner.trySpawn(engine, Direction.NORTH);
                case DOWN -> spawner.trySpawn(engine, Direction.SOUTH);
                case LEFT -> spawner.trySpawn(engine, Direction.WEST);
                case RIGHT -> spawner.trySpawn(engine, Direction.EAST);
                case R -> spawner.trySpawnRandom(engine);
            }
        });
    }
}