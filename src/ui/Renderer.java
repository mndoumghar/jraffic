package ui;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import model.Direction;
import model.Vehicle;
import simulation.SimulationEngine;
import traffic.TrafficController;
import utils.Constants;

public class Renderer {

    private static final double CX     = Constants.CENTER_X;
    private static final double CY     = Constants.CENTER_Y;
    private static final double HALF_R = Constants.ROAD_WIDTH / 2.0;
    private static final double W      = Constants.WINDOW_WIDTH;
    private static final double H      = Constants.WINDOW_HEIGHT;

    public void draw(GraphicsContext gc, SimulationEngine engine) {
        drawBackground(gc);
        drawRoads(gc);
        drawTrafficLights(gc, engine.getController());
        for (Vehicle v : engine.getVehicles()) {
            gc.setFill(v.getColor());
            gc.fillRect((int) v.getX(), (int) v.getY(), 30, 30);
        }
    }

    private void drawBackground(GraphicsContext gc) {
        gc.setFill(Color.web("#2E4E2C"));
        gc.fillRect(0, 0, W, H);
    }

    private void drawRoads(GraphicsContext gc) {
        gc.setFill(Color.web("#2c2c2c"));
        gc.fillRect(0,       CY - HALF_R, W, Constants.ROAD_WIDTH);
        gc.fillRect(CX - HALF_R, 0,       Constants.ROAD_WIDTH, H);

        gc.setStroke(Color.web("#FFD700"));
        gc.setLineWidth(2);
        gc.setLineDashes(15, 15);
        gc.strokeLine(0,        CY,      CX - HALF_R, CY);
        gc.strokeLine(CX + HALF_R, CY,  W,            CY);
        gc.strokeLine(CX, 0,        CX,  CY - HALF_R);
        gc.strokeLine(CX, CY + HALF_R,  CX,           H);
        gc.setLineDashes(0);

        gc.setStroke(Color.WHITE);
        gc.setLineWidth(3);
        gc.strokeLine(0,          CY - HALF_R, CX - HALF_R,  CY - HALF_R);
        gc.strokeLine(CX + HALF_R, CY - HALF_R, W,            CY - HALF_R);
        gc.strokeLine(0,          CY + HALF_R, CX - HALF_R,  CY + HALF_R);
        gc.strokeLine(CX + HALF_R, CY + HALF_R, W,            CY + HALF_R);
        gc.strokeLine(CX - HALF_R, 0,           CX - HALF_R, CY - HALF_R);
        gc.strokeLine(CX - HALF_R, CY + HALF_R, CX - HALF_R, H);
        gc.strokeLine(CX + HALF_R, 0,           CX + HALF_R, CY - HALF_R);
        gc.strokeLine(CX + HALF_R, CY + HALF_R, CX + HALF_R, H);
    }

    private void drawTrafficLights(GraphicsContext gc, TrafficController tc) {
        double gap = 60.0;
        int    r   = 16;
        Direction state = tc.state;

        drawLight(gc, CX - gap - 23, CY - gap - 23, r, state == Direction.SOUTH);
        drawLight(gc, CX + gap + 7,  CY - gap - 23, r, state == Direction.WEST);
        drawLight(gc, CX - gap - 23, CY + gap + 7,  r, state == Direction.EAST);
        drawLight(gc, CX + gap + 7,  CY + gap + 7,  r, state == Direction.NORTH);

        gc.setFill(tc.isClearing() ? Color.YELLOW : Color.LIMEGREEN);
        gc.fillText(tc.isClearing() ? "SWITCHING..."
                : "Active: " + state, 10, 30);
        gc.setFill(Color.WHITE);
        gc.fillText(String.format("Timer: %.1fs", tc.getTimer()), 10, 50);
    }

    private void drawLight(GraphicsContext gc, double x, double y, int r, boolean green) {
        gc.setFill(green ? Color.LIMEGREEN : Color.RED);
        gc.fillOval(x, y, r, r);
    }
}