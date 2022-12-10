package com.javable.daleks.logic;

import com.javable.daleks.Settings;
import com.javable.daleks.enums.EObjectType;
import javafx.scene.image.Image;
import kotlin.NotImplementedError;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ImageLoader {
    private final Image PlayerImg, EmptyImg;

    // TODO reszta textur

    public ImageLoader() throws FileNotFoundException {
        PlayerImg = new Image(new FileInputStream(Settings.PlayerImage));
        EmptyImg = new Image(new FileInputStream(Settings.EmptyImage));
    }

    public Image ToImage(EObjectType type) {
        return switch (type) {
            case Empty -> EmptyImg;
            case Player -> PlayerImg;
            default -> throw new NotImplementedError();
            // TODO reszta opcji
        };
    }
}
