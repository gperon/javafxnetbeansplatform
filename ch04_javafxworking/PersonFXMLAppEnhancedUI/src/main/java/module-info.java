module org.gperon.personfxmlappenhancedui {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;

    opens org.gperon.personfxmlapp to javafx.fxml;
    exports org.gperon.personfxmlapp;
}
