package com.javable.daleks.models.objects.powers;

import com.javable.daleks.logic.ImageLoader;
import com.javable.daleks.models.GameMap;
import com.javable.daleks.models.Position;
import com.javable.daleks.models.objects.*;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;

import java.util.Optional;

public class Attractor extends PowerUp implements Destination {
    private boolean isActive = false;

    public Attractor(Position position) {
        super(position);
    }

    @Override
    public Image getImage(ImageLoader loader) {
        return loader.getAttractorImage();
    }

    @Override
    public Optional<Effect> getEffect(ImageLoader loader) {
        if(isActive)
            return Optional.empty();
        return Optional.of(loader.getInactiveAttractorEffect());
    }

    @Override
    public void collide(GameMap map, Player player, boolean inWalk) {
        if(!isActive) {
            isActive = true;
            map.addDestination(this);
        }
        else{
            isActive = false;
            map.removeDestination(this);
        }
    }

    @Override
    public void collide(GameMap map, Scrap scrap, boolean inWalk) {
        throw new IllegalStateException("Scrap can't collide with attractor");
    }

    @Override
    public void collide(GameMap map, Dalek dalek, boolean inWalk) {
        if(isActive){
            map.removeDalek(dalek);
            map.removeDestination(this);
            map.removeObject(this);
            map.addObject(new Scrap(this.position));
        }
        else{
            map.removeObject(this);
        }
    }

    @Override
    public void collide(GameMap map, PowerUp powerUp, boolean inWalk) {
        throw new IllegalStateException("PowerUp can't collide with powerUp");
    }

    @Override
    public Position getPosition() {
        return position;
    }
}
