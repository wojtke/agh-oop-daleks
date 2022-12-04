module com.javable.daleks {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;

    opens com.javable.daleks to javafx.fxml;
    exports com.javable.daleks;
    exports com.javable.daleks.controllers;
    opens com.javable.daleks.controllers to javafx.fxml;
}