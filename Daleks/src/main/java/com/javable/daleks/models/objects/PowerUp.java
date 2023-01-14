package com.javable.daleks.models.objects;

import com.javable.daleks.logic.ImageLoader;
import com.javable.daleks.models.GameMap;
import com.javable.daleks.models.Position;
import javafx.scene.image.Image;

public abstract class PowerUp extends ObjectBase{

    public PowerUp(Position position) {
        super(position);
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
