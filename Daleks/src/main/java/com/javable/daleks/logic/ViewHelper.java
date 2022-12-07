package com.javable.daleks.logic;

import com.javable.daleks.DaleksApp;
import com.javable.daleks.Settings;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;

public class ViewHelper {
    public static void SetScene(String viewPath) throws IOException {
        Scene scene = new Scene(
                new FXMLLoader(DaleksApp.class.getResource(viewPath)).load(),
                Settings.WindowWidth,
                Settings.WindowHeight);
        //scene.getStylesheets().add(getClass().getResource("styles/style.css").toExternalForm());
        scene.getRoot().setStyle("-fx-base:black");
        DaleksApp.GetStage().setScene(scene);
    }
}
