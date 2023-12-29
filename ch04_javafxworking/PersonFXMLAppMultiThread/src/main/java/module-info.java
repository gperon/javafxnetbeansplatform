module org.gperon.personfxmlappmultithread {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;

    opens org.gperon.personfxmlappmultithread to javafx.fxml;
    exports org.gperon.personfxmlappmultithread;
}
