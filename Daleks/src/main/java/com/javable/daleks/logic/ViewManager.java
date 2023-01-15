package com.javable.daleks.logic;

import com.javable.daleks.DaleksApp;
import com.javable.daleks.Settings;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;

public class ViewManager {
    public static void SetScene(String viewPath) {
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
        SetScene(scene);
    }

    public static void SetScene(Scene scene) {
        DaleksApp.GetStage().setScene(scene);
    }
}
