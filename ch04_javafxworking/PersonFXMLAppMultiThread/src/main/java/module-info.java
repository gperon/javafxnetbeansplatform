module org.gperon.personfxmlappmultithread {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;

    opens org.gperon.personfxmlapp to javafx.fxml;
    exports org.gperon.personfxmlapp;
}
