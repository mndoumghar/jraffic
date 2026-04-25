# JavaFX Traffic Simulation
src/
├── input/
│   └── KeyboardHandler.java      # Captures user keyboard input
├── model/
│   ├── Direction.java            # Cardinal direction enum
│   └── Vehicle.java              # Car physics and positional logic
├── simulation/
│   ├── CollisionManager.java     # Bounding boxes and safe distance calculations
│   ├── SimulationEngine.java     # Core loop orchestrator
│   └── SpawnManager.java         # Vehicle generation and cooldown logic
├── traffic/
│   ├── TrafficController.java    # Manages light switching logic
│   └── TrafficLight.java         # Boolean state representing green/red
├── ui/
│   ├── MainApp.java              # JavaFX entry point and AnimationTimer
│   └── Renderer.java             # Graphical drawing engine
└── utils/
    └── Constants.java            # Unified metrics for roads and simulation

## HOw To  install javax

- `cd ~`
- ` wget https://download2.gluonhq.com/openjfx/21.0.2/openjfx-21.0.2_linux-x64_bin-sdk.zip`

- `unzip openjfx-21.0.2_linux-x64_bin-sdk.zip`

- ` mv javafx-sdk-21.0.2 javafx-sdk`

 ## run Jraffic

 - ``./run.sh``
 