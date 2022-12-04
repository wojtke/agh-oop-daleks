package com.javable.daleks;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


/* TODO Milestone 1:
    - Przygotować grę w Daleki, uwzględniając szczególnie mechanizm kolizji między obiektami (uwaga na zasadę LSP)
    - Wizualizacja gry: możliwość rozegrania pojedynczej pełnej rozgrywki.
    - Wizualizacja końca gry (przegrana/ wygrana)
    - Możliwość parametryzowania rozgrywki (liczba Daleków, pozycje startowe Daleków i Doktora, rozmiar mapy)
    - Zapewnić możliwość sterowania rozgrywką (interfejs do klikania lub obsługa klawiatury)
    - Wstrzykiwanie zależności: Spring lub Guice
*/

public class DaleksApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(
                new FXMLLoader(DaleksApp.class.getResource("main-view.fxml")).load(),
                Settings.WindowWidth,
                Settings.WindowHeight);
        //scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        scene.getRoot().setStyle("-fx-base:black");

        stage.setTitle("Javable's Daleks");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}