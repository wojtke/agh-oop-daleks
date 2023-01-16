package com.javable.daleks.logic;

import com.javable.daleks.Settings;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ImageLoader {
    private final Image playerImg, emptyImg, dalekImg, scrapImg, attractorImg, teleporterImg;

    public ImageLoader() throws FileNotFoundException {
        playerImg = new Image(new FileInputStream(Settings.PlayerImage));
        emptyImg = new Image(new FileInputStream(Settings.EmptyImage));
        dalekImg = new Image(new FileInputStream(Settings.DalekImage));
        scrapImg = new Image(new FileInputStream(Settings.ScrapImage));
        attractorImg = new Image(new FileInputStream(Settings.AttractorImage));
        teleporterImg = new Image(new FileInputStream(Settings.TeleporterImage));

    }

    public Image getPlayerImage() {
        return playerImg;
    }
    public Image getEmptyImage() {
        return emptyImg;
    }
    public Image getDalekImage() {
        return dalekImg;
    }
    public Image getScrapImage() {
        return scrapImg;
    }

    public Image getAttractorImage() {
        return attractorImg;
    }
    public Effect getInactiveAttractorEffect() {
        return new ColorAdjust(1, 1, 0.5, 0.5);
    }
    public Image getTeleporterImage() {
        return teleporterImg;
    }
}
