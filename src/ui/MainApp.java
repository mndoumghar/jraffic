package ui;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import input.KeyboardHandler;
import simulation.SimulationEngine;
import traffic.TrafficController;
import utils.Constants;

public class MainApp extends Application {
    private Renderer renderer = new Renderer();
    private TrafficController controller = new TrafficController();
    private SimulationEngine engine = new SimulationEngine(controller);

    @Override
    public void start(Stage stage) {
        Canvas canvas = new Canvas(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        Pane root = new Pane(canvas);
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("Traffic Simulation");
        stage.setResizable(false);
        stage.show();

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
        renderer.draw(gc, engine);
    }

    public static void main(String[] args) {
        launch();
    }
}