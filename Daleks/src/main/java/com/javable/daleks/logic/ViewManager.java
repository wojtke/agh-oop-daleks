package com.javable.daleks.logic;

import com.javable.daleks.DaleksApp;
import com.javable.daleks.Settings;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;

public class ViewManager {
    public static void setScene(String viewPath) {
        Scene scene;
        try {
            scene = new Scene(
                    new FXMLLoader(DaleksApp.class.getResource(viewPath)).load(),
                    Settings.WindowWidth,
                    Settings.WindowHeight);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        scene.getRoot().setStyle("-fx-base:black");
        setScene(scene);
    }

    public static void setScene(Scene scene) {
        DaleksApp.getStage().setScene(scene);
    }
}
