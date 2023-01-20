package com.javable.daleks.models.objects.characters;

import com.javable.daleks.Settings;
import com.javable.daleks.interfaces.Destination;
import com.javable.daleks.logic.ImageLoader;
import com.javable.daleks.logic.ViewManager;
import com.javable.daleks.models.GameMap;
import com.javable.daleks.models.Position;
import com.javable.daleks.models.objects.ObjectBase;
import com.javable.daleks.models.objects.powersUps.PowerUp;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;

import java.util.Optional;

import static java.lang.Math.abs;

public class Player extends ObjectBase implements Destination {

    public Player(Position position) {
        super(position);
    }

    @Override
    public Image getImage(ImageLoader loader) {
        return loader.getPlayerImage();
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
        throw new IllegalStateException("Player can't collide with player");
    }

    @Override
    public void collide(GameMap map, Scrap scrap, boolean inWalk) {
        gameOver();
    }

    @Override
    public void collide(GameMap map, Dalek dalek, boolean inWalk) {
        gameOver();
    }

    @Override
    public void collide(GameMap map, PowerUp powerUp, boolean inWalk) {

    }

    public boolean canMove(Position newPosition) {
        if(abs(newPosition.x - position.x) > 1 || abs(newPosition.y - position.y) > 1)
            return false;

        return newPosition != position;
    }

    private void gameOver() {
        ViewManager.setScene(Settings.GameOverView);
    }

    @Override
    public Position getPosition() {
        return position;
    }
}
