module org.example.bro {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.bro to javafx.fxml;
    exports org.example.bro;
}