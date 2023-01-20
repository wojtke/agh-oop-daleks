package com.javable.daleks.models.objects.characters;

import com.javable.daleks.logic.ImageLoader;
import com.javable.daleks.models.GameMap;
import com.javable.daleks.models.Position;
import com.javable.daleks.models.objects.ObjectBase;
import com.javable.daleks.models.objects.powersUps.PowerUp;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;

import java.util.Optional;

public class Scrap extends ObjectBase {

    public Scrap(Position position) {
        super(position);
    }

    @Override
    public Image getImage(ImageLoader loader) {
        return loader.getScrapImage();
    }

    @Override
    public Optional<Effect> getEffect(ImageLoader loader) {
        return Optional.empty();
    }

    @Override
    public void createCollision(GameMap map, ObjectBase other, boolean inWalk) {
        other.collide(map, this, inWalk);
    }

    @Override
    public void collide(GameMap map, Player player, boolean inWalk) {

    }

    @Override
    public void collide(GameMap map, Scrap scrap, boolean inWalk) {

    }

    @Override
    public void collide(GameMap map, Dalek dalek, boolean inWalk) {

    }

    @Override
    public void collide(GameMap map, PowerUp powerUp, boolean inWalk) {

    }

}
