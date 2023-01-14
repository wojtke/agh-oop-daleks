package com.javable.daleks.models.objects;

import com.javable.daleks.logic.ImageLoader;
import com.javable.daleks.models.GameMap;
import com.javable.daleks.models.Position;
import javafx.scene.image.Image;

public class Scrap extends ObjectBase {

    public Scrap(Position position) {
        super(position);
    }

    @Override
    public Image getImage(ImageLoader loader) {
        return loader.getScrapImage();
    }

    @Override
    public void createCollision(GameMap map, ObjectBase other, boolean inWalk) {
        other.collide(map, this, inWalk);
    }

    @Override
    public void collide(GameMap map, Player other, boolean inWalk) {

    }

    @Override
    public void collide(GameMap map, Scrap other, boolean inWalk) {

    }

    @Override
    public void collide(GameMap map, Dalek other, boolean inWalk) {

    }

    @Override
    public void collide(GameMap map, PowerUp other, boolean inWalk) {

    }

}
