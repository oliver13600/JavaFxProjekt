module com.example.basiclayour {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    requires org.hibernate.orm.core;
    requires java.naming;
    requires jakarta.persistence;

    requires java.net.http;
    requires com.fasterxml.jackson.databind;

    requires kernel;
    requires layout;
    requires io;

    opens com.example.basiclayour.dto to com.fasterxml.jackson.databind;

    opens com.example.basiclayour.model to org.hibernate.orm.core, javafx.base;

    opens com.example.basiclayour to javafx.fxml;
    opens com.example.basiclayour.view to javafx.fxml;

    exports com.example.basiclayour;
    opens com.example.basiclayour.repository to org.hibernate.orm.core;
    opens com.example.basiclayour.service to com;
}