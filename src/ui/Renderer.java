package ui;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import model.Vehicle;
import simulation.SimulationEngine;
import traffic.TrafficController;
import utils.Constants;

public class Renderer {
    
    public void draw(GraphicsContext gc, SimulationEngine engine) {
        gc.setFill(Color.web("#2E4E2C"));
        gc.fillRect(0, 0, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);

        double w = Constants.WINDOW_WIDTH;
        double h = Constants.WINDOW_HEIGHT;
        double cx = Constants.CENTER_X;
        double cy = Constants.CENTER_Y;
        double rw = Constants.ROAD_WIDTH;
        double halfR = rw / 2.0;

        gc.setFill(Color.web("#2c2c2c"));
        gc.fillRect(0, cy - halfR, w, rw);
        gc.fillRect(cx - halfR, 0, rw, h);

        gc.setStroke(Color.web("#FFD700"));
        gc.setLineWidth(2);
        gc.setLineDashes(15, 15);

        gc.strokeLine(0, cy, cx - halfR, cy);
        gc.strokeLine(cx + halfR, cy, w, cy);
        
        gc.strokeLine(cx, 0, cx, cy - halfR);
        gc.strokeLine(cx, cy + halfR, cx, h);

        gc.setLineDashes(0);
        
        gc.setStroke(Color.WHITE);
        gc.setLineWidth(3);
        
        gc.strokeLine(0, cy - halfR, cx - halfR, cy - halfR);
        gc.strokeLine(cx + halfR, cy - halfR, w, cy - halfR);
        gc.strokeLine(0, cy + halfR, cx - halfR, cy + halfR);
        gc.strokeLine(cx + halfR, cy + halfR, w, cy + halfR);
        
        gc.strokeLine(cx - halfR, 0, cx - halfR, cy - halfR);
        gc.strokeLine(cx - halfR, cy + halfR, cx - halfR, h);
        gc.strokeLine(cx + halfR, 0, cx + halfR, cy - halfR);
        gc.strokeLine(cx + halfR, cy + halfR, cx + halfR, h);

        drawTrafficLights(gc, engine.getController());

        for (Vehicle v : engine.getVehicles()) {
            drawVehicle(gc, v);
        }
    }
    
    private void drawTrafficLights(GraphicsContext gc, TrafficController controller) {
        double offset = Constants.ROAD_WIDTH / 2.0 + 15;
        double cx = Constants.CENTER_X;
        double cy = Constants.CENTER_Y;
        
        boolean nsGreen = controller.getNorthSouthLight().isGreen();
        gc.setFill(nsGreen ? Color.LIMEGREEN : Color.RED);
        gc.fillOval(cx - offset - 10, cy - offset - 10, 20, 20);
        gc.fillOval(cx + offset - 10, cy + offset - 10, 20, 20);

        boolean ewGreen = controller.getEastWestLight().isGreen();
        gc.setFill(ewGreen ? Color.LIMEGREEN : Color.RED);
        gc.fillOval(cx + offset - 10, cy - offset - 10, 20, 20);
        gc.fillOval(cx - offset - 10, cy + offset - 10, 20, 20);
    }

    private void drawVehicle(GraphicsContext gc, Vehicle v) {
        gc.setFill(Color.web("#3498DB"));
        gc.setStroke(Color.web("#2980B9"));
        gc.setLineWidth(2);
        
        double x = v.getX();
        double y = v.getY();
        double width = Constants.VEHICLE_WIDTH;
        double length = Constants.VEHICLE_LENGTH;

        gc.fillRect(x, y, width, length);
        gc.strokeRect(x, y, width, length);
        
        gc.setFill(Color.YELLOW);
        switch (v.getDirection()) {
            case NORTH -> {
                gc.fillRect(x + 2, y, 4, 4);
                gc.fillRect(x + width - 6, y, 4, 4);
            }
            case SOUTH -> {
                gc.fillRect(x + 2, y + length - 4, 4, 4);
                gc.fillRect(x + width - 6, y + length - 4, 4, 4);
            }
            case EAST -> {
                gc.fillRect(x + width - 4, y + 2, 4, 4);
                gc.fillRect(x + width - 4, y + length - 6, 4, 4);
            }
            case WEST -> {
                gc.fillRect(x, y + 2, 4, 4);
                gc.fillRect(x, y + length - 6, 4, 4);
            }
        }
    }
}