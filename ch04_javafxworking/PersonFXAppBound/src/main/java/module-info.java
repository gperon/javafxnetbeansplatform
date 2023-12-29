module org.gperon.personfxappbound {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.gperon.personfxappbound to javafx.fxml;
    exports org.gperon.personfxappbound;
}
