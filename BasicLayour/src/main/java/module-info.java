module com.example.basiclayour {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.basiclayour to javafx.fxml;
    exports com.example.basiclayour;
}