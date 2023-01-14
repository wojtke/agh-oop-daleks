package com.javable.daleks.logic;

import com.javable.daleks.Settings;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ImageLoader {
    private final Image playerImg, emptyImg, dalekImg, scrapImg;

    public ImageLoader() throws FileNotFoundException {
        playerImg = new Image(new FileInputStream(Settings.PlayerImage));
        emptyImg = new Image(new FileInputStream(Settings.EmptyImage));
        dalekImg = new Image(new FileInputStream(Settings.DalekImage));
        scrapImg = new Image(new FileInputStream(Settings.ScrapImage));
    }
//
//    public Image GetImage(EObjectType type) {
//        return switch (type) {
//            case EMPTY -> emptyImg;
//            case PLAYER -> playerImg;
//            case DALEK -> dalekImg;
//            case SCRAP -> scrapImg;
//        };
//    }
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

}
