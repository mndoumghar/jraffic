package traffic;

import model.Direction;
import java.util.Arrays;

public class TrafficController {

    private static final Direction[] CYCLE = {
        Direction.SOUTH, Direction.WEST, Direction.NORTH, Direction.EAST
    };

    public  Direction state;
    private Direction nextState;
    private double    timer;
    private boolean   clearing;

    public TrafficController() {
        state    = Direction.SOUTH;
        nextState = Direction.WEST;
        timer    = 1.0;
        clearing = false;
    }

    public void update(double dt, int north, int south, int east, int west, int capacity) {
        if (timer > 0.0) timer -= dt;

        if (clearing) {
            if (timer <= 0.0) {
                state    = nextState;
                clearing = false;
                timer    = greenDuration(countForState(north, south, east, west), capacity);
            }
        } else {
            if (timer <= 0.0) {
                nextState = priorityLane(north, south, east, west);
                state     = null;
                clearing  = true;
                timer     = 0.5;
            }
        }
    }

    private double greenDuration(int count, int capacity) {
        double ratio = (double) count / capacity;
        if (ratio > 0.4) return 2.0;
        if (count  > 0)  return 1.0;
        return 0.5;
    }

    private int countForState(int north, int south, int east, int west) {
        if (state == Direction.NORTH) return north;
        if (state == Direction.SOUTH) return south;
        if (state == Direction.EAST)  return east;
        if (state == Direction.WEST)  return west;
        return 0;
    }

    private Direction priorityLane(int north, int south, int east, int west) {
        int[] counts = { south, west, north, east };

        int currentIdx = 0;
        for (int i = 0; i < CYCLE.length; i++) {
            if (CYCLE[i] == state) { currentIdx = i; break; }
        }

        int[] others = new int[3];
        int k = 0;
        for (int i = 0; i < counts.length; i++) {
            if (i != currentIdx) others[k++] = counts[i];
        }
        Arrays.sort(others);
        int max = others[2];

        for (int i = 0; i < counts.length; i++) {
            if (counts[i] == max && i != currentIdx) return CYCLE[i];
        }
        return CYCLE[(currentIdx + 1) % CYCLE.length];
    }

    public double  getTimer()    { return timer; }
    public boolean isClearing()  { return clearing; }
}