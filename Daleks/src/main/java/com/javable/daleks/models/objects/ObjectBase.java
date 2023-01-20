package com.javable.daleks.models.objects;

import com.javable.daleks.logic.ImageLoader;
import com.javable.daleks.models.GameMap;
import com.javable.daleks.models.Position;
import com.javable.daleks.models.objects.characters.Dalek;
import com.javable.daleks.models.objects.characters.Player;
import com.javable.daleks.models.objects.characters.Scrap;
import com.javable.daleks.models.objects.powersUps.PowerUp;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;

import java.util.Optional;

public abstract class ObjectBase {
    public Position position;

    public ObjectBase(Position position) {
        this.position = position;
    }

    public abstract Image getImage(ImageLoader loader);
    public abstract Optional<Effect> getEffect(ImageLoader loader);
    public abstract void createCollision(GameMap map, ObjectBase other, boolean inWalk);
    public abstract void collide(GameMap map, Player player, boolean inWalk);
    public abstract void collide(GameMap map, Scrap scrap, boolean inWalk);
    public abstract void collide(GameMap map, Dalek dalek, boolean inWalk);
    public abstract void collide(GameMap map, PowerUp powerUp, boolean inWalk);
}
