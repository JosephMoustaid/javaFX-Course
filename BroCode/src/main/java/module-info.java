module org.example.brocode {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.brocode to javafx.fxml;
    exports org.example.brocode;
}