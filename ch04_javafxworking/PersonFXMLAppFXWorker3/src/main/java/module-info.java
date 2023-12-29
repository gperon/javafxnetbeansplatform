module org.gperon.personfxmlapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;

    opens org.gperon.personfxmlapp to javafx.fxml;
    exports org.gperon.personfxmlapp;
    opens org.gperon.familytree.model to javafx.fxml;
    exports org.gperon.familytree.model;
}
