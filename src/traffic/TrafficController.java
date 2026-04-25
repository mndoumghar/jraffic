package traffic;

public class TrafficController {

    private TrafficLight lightNorthSouth = new TrafficLight();
    private TrafficLight lightEastWest = new TrafficLight();

    private long lastSwitchTime = 0;
    private boolean nsGreen = true;

    public TrafficController() {
        lightNorthSouth = new TrafficLight();
        lightEastWest = new TrafficLight();

        lightNorthSouth.setGreen(true);
        lightEastWest.setGreen(false);
    }

    public void update(long now) {

        if (now - lastSwitchTime > 3_000_000_000L) {

            nsGreen = !nsGreen;

            lightNorthSouth.setGreen(nsGreen);
            lightEastWest.setGreen(!nsGreen);

            lastSwitchTime = now;
        }
    }

    public TrafficLight getNorthSouthLight() {
        return lightNorthSouth;
    }

    public TrafficLight getEastWestLight() {
        return lightEastWest;
    }
}