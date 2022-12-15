package com.javable.daleks.logic;

import com.javable.daleks.Settings;
import com.javable.daleks.enums.EObjectType;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ImageLoader {
    private final Image PlayerImg, EmptyImg, DalekImg, ScrapImg;

    public ImageLoader() throws FileNotFoundException {
        PlayerImg = new Image(new FileInputStream(Settings.PlayerImage));
        EmptyImg = new Image(new FileInputStream(Settings.EmptyImage));
        DalekImg = new Image(new FileInputStream(Settings.DalekImage));
        ScrapImg = new Image(new FileInputStream(Settings.ScrapImage));
    }

    public Image getImage(EObjectType type) {
        return switch (type) {
            case Empty  -> EmptyImg;
            case Player -> PlayerImg;
            case Dalek  -> DalekImg;
            case Scrap  -> ScrapImg;
        };
    }

}
