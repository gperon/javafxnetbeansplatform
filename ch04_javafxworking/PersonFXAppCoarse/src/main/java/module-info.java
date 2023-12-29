module org.gperon.personfxappcoarse {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.gperon.personfxappcoarse to javafx.fxml;
    exports org.gperon.personfxappcoarse;
}
