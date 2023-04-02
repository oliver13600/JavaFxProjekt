module com.example.basiclayour {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens com.example.basiclayour to javafx.fxml;
    exports com.example.basiclayour;
}