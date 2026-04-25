package ui;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import input.KeyboardHandler;

import model.Vehicle;
import model.Direction;
import simulation.SimulationEngine;
import traffic.TrafficController;

public class MainApp extends Application {
    Renderer renderer = new Renderer();

    TrafficController controller = new TrafficController();
    SimulationEngine engine = new SimulationEngine(controller);
    @Override
    public void start(Stage stage) {
        Canvas canvas = new Canvas(800, 800);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        Pane root = new Pane(canvas);
        Scene scene = new Scene(root);

           
        stage.setScene(scene);
        stage.setTitle("Traffic Simulation");
        stage.show();
               // engine.addVehicle(new Vehicle(400, 700, Direction.WEST));
        new KeyboardHandler(scene, engine);

        new AnimationTimer() {
            @Override
            public void handle(long now) {
                engine.update(now);
                draw(gc);

            }
        }.start();
    }

   private void draw(GraphicsContext gc) {

    //gc.clearRect(0, 0, 800, 800);

    renderer.drawRoads(gc);

    gc.setFill(javafx.scene.paint.Color.RED);

   for (Vehicle v : engine.getVehicles()) {
    System.out.println("CAR: " + v.getX() + " " + v.getY());
    gc.fillRect(v.getX(), v.getY(), 20, 20);
}
}

    public static void main(String[] args) {
        launch();
    }
}