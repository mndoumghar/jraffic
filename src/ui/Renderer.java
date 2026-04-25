package ui;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Renderer {
    
    public void drawRoads(GraphicsContext gc) {

        gc.setFill(Color.DARKGRAY);
        gc.fillRect(0, 0, 800, 800);

        gc.setFill(Color.LIGHTGRAY);

        // 🚗 horizontal road
        gc.fillRect(0, 350, 800, 100);

        // 🚗 vertical road
        gc.fillRect(350, 0, 100, 800);

        // lines (optional)
        gc.setStroke(Color.WHITE);
        gc.setLineDashes(10);

        // center lines
        gc.strokeLine(0, 400, 800, 400);
        gc.strokeLine(400, 0, 400, 800);

        gc.setLineDashes(0);
    }
}