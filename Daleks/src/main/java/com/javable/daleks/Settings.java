package com.javable.daleks;

public class Settings  {
    // stałe programu
    public static final int
            WindowHeight = 600,
            WindowWidth = 1000;

    public static final String
            MainView = "views/main-view.fxml",
            GameOverView = "views/game-over-view.fxml",
            PlayerImage = "src/main/resources/com/javable/daleks/images/player.jpg",
            DalekImage = "src/main/resources/com/javable/daleks/images/dalek.png",

            ScrapImage = "src/main/resources/com/javable/daleks/images/scrap.jpg",
            EmptyImage = "src/main/resources/com/javable/daleks/images/empty.jpg";

    // ustawienia
    public static int
            GridSize = 50,
            GridCount = 10,
            DaleksCount = 5,
            StartX = 4,
            StartY = 4;
}
