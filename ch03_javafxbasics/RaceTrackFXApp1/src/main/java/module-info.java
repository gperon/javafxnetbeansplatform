module org.gperon.racetrackfxapp1 {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.gperon.racetrackfxapp1 to javafx.fxml;
    exports org.gperon.racetrackfxapp1;
}
