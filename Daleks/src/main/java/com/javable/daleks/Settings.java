package com.javable.daleks;

public class Settings  {
    // stałe programu
    public static final int
            WindowHeight = 600,
            WindowWidth = 1000;

    public static final String
            MainView = "views/main-view.fxml",
            GameOverView = "views/game-over-view.fxml",
            GameWonView = "views/game-won-view.fxml",
            LevelSelectView = "views/level-select-view.fxml",
            CampaignView = "views/campaign-view.fxml",

            PlayerImage = "src/main/resources/com/javable/daleks/images/mario.gif",
            DalekImage = "src/main/resources/com/javable/daleks/images/goomba.gif",
            ScrapImage = "src/main/resources/com/javable/daleks/images/brick_wall.png",
            EmptyImage = "src/main/resources/com/javable/daleks/images/tile.png",
            AttractorImage = "src/main/resources/com/javable/daleks/images/star.png",
            TeleporterImage = "src/main/resources/com/javable/daleks/images/pipe.png",

            ServiceUrl = "http://localhost:8080", // "http://localhost/javable"
            GetUserLevels = "/levels",
            GetCampaignLevels = "/levels/campaign",
            PostLevel = "/levels",
            DeleteLevel = "/levels",

            UserCampaign = "src/main/resources/com/javable/daleks/campaign.csv";
}
