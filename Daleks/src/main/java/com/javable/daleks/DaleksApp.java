package com.javable.daleks;

import com.javable.daleks.controllers.MainController;
import com.javable.daleks.models.GameMapSettings;
import com.javable.daleks.service.ServiceManager;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;


/* TODO Milestone 2:
    - Pobieranie poziomów
    - Wysyłanie nowych poziomów
    - Wybór poziomu z usuwaniem
    - Json serializer
*/

/* DONE Milestone 2:
    - ServiceManager komunikujący się z serwisem
    - Endpoint GetLevels
    - Endpoint UploadLevel
    - Endpoint DeleteLevel
*/

/* DONE Milestone 1:
    - Przygotować grę w Daleki, uwzględniając szczególnie mechanizm kolizji między obiektami (uwaga na zasadę LSP)
    - Wizualizacja gry: możliwość rozegrania pojedynczej pełnej rozgrywki.
    - Możliwość parametryzowania rozgrywki (liczba Daleków, pozycje startowe Daleków i Doktora, rozmiar mapy)
    - Zapewnić możliwość sterowania rozgrywką (interfejs do klikania lub obsługa klawiatury)
    - Wstrzykiwanie zależności: Spring lub Guice
    - Wizualizacja końca gry (przegrana/ wygrana)
*/

public class DaleksApp extends Application {
    private static MainController MainController;
    private static Stage MainStage;

    public static Stage GetStage() {
        return MainStage;
    }

    public static MainController GetMainController() {
        return MainController;
    }

    public static void main(String[] args) {
        ServiceManager service = new ServiceManager();
        // TODO przykłądowe wywołąnia, do usunięcia
        service.GetAllLevels();
        service.UploadLevel(new GameMapSettings(10, 22, "test4"));
        service.DeleteLevel("test4");

        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        MainStage = stage;
        MainController = new MainController();
        MainController.InitView();
        MainStage.setTitle("Javable's Daleks");
        MainStage.show();
    }
}