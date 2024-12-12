module org.example.brocodecourse {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens org.example.brocodecourse to javafx.fxml;
    exports org.example.brocodecourse;
    exports PRIM;
    exports PrimProject;
    opens PRIM to javafx.fxml;
}