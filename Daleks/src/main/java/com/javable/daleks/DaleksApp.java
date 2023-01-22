package com.javable.daleks;

import com.javable.daleks.controllers.MainController;
import com.javable.daleks.logic.CampaignManager;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;


/* TODO Milestone 2:
    - Przeszukiwanie poziomów po parametrach
    - Konfiguracja mapy z konkretnymi pozycjami startowymi
*/

/* DONE Milestone 2:
    - Pobieranie poziomów
    - Wysyłanie nowych poziomów
    - Wybór poziomu z usuwaniem
    - Json serializer
    - ServiceManager komunikujący się z serwisem
    - Endpoint GetLevels
    - Endpoint uploadLevel
    - Endpoint deleteLevel
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
    private static MainController mainController;
    private static Stage mainStage;
    private static final CampaignManager campaignManager = new CampaignManager();

    public static Stage getStage() {
        return mainStage;
    }

    public static MainController getMainController() {
        return mainController;
    }
    public static CampaignManager getCampaignManager() { return campaignManager; }

    public static void main(String[] args) {
        launch();
    }

    public static void QuitGame() { System.exit(0); }

    @Override
    public void start(Stage stage) throws IOException {
        mainStage = stage;
        mainController = new MainController();
        mainController.initView();
        mainStage.setTitle("Javable's Daleks");
        mainStage.show();
    }
}