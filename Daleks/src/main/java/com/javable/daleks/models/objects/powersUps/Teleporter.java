package com.javable.daleks.models.objects.powersUps;

import com.javable.daleks.logic.ImageLoader;
import com.javable.daleks.logic.MoveHandler;
import com.javable.daleks.models.GameMap;
import com.javable.daleks.models.Position;
import com.javable.daleks.models.objects.characters.Dalek;
import com.javable.daleks.models.objects.characters.Player;
import com.javable.daleks.models.objects.characters.Scrap;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;

import java.util.Optional;

public class Teleporter extends PowerUp {
    private final MoveHandler moveHandler;

    public Teleporter(Position position, MoveHandler moveHandler) {
        super(position);
        this.moveHandler = moveHandler;
    }

    @Override
    public Image getImage(ImageLoader loader) {
        return loader.getTeleporterImage();
    }

    @Override
    public Optional<Effect> getEffect(ImageLoader loader) {
        return Optional.empty();
    }

    @Override
    public void collide(GameMap map, Player player, boolean inWalk) {
        moveHandler.teleportPlayer();
        map.removeObject(this);
    }

    @Override
    public void collide(GameMap map, Scrap scrap, boolean inWalk) {
        throw new IllegalStateException("Scrap can't collide with teleporter");
    }

    @Override
    public void collide(GameMap map, Dalek dalek, boolean inWalk) {
        map.removeObject(this);
    }

    @Override
    public void collide(GameMap map, PowerUp powerUp, boolean inWalk) {
        throw new IllegalStateException("Teleporter can't collide with power up");
    }
}
