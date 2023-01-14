package com.javable.daleks.models.objects;

import com.javable.daleks.logic.ImageLoader;
import com.javable.daleks.models.GameMap;
import com.javable.daleks.models.Position;
import javafx.scene.image.Image;

public abstract class ObjectBase {
    public Position position;

    public ObjectBase(Position position) {
        this.position = position;
    }

    public abstract Image getImage(ImageLoader loader);
    public abstract void createCollision(GameMap map, ObjectBase other, boolean inWalk);
    public abstract void collide(GameMap map, Player other, boolean inWalk);
    public abstract void collide(GameMap map, Scrap other, boolean inWalk);
    public abstract void collide(GameMap map, Dalek other, boolean inWalk);
    public abstract void collide(GameMap map, PowerUp other, boolean inWalk);
}
