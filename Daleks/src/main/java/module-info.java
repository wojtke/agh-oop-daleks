module com.javable.daleks {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;
    requires com.google.guice;
    requires org.json;


    opens com.javable.daleks to javafx.fxml;
    exports com.javable.daleks;
    exports com.javable.daleks.controllers;
    opens com.javable.daleks.controllers to javafx.fxml;
    exports com.javable.daleks.logic;
    opens com.javable.daleks.logic to javafx.fxml;
    exports com.javable.daleks.models;

    exports com.javable.daleks.models.objects;
    exports com.javable.daleks.enums;
    exports com.javable.daleks.interfaces;
    opens com.javable.daleks.interfaces to javafx.fxml;
}