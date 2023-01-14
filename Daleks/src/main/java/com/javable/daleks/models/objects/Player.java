package com.javable.daleks.models.objects;

import com.javable.daleks.Settings;
import com.javable.daleks.logic.ImageLoader;
import com.javable.daleks.logic.ViewManager;
import com.javable.daleks.models.GameMap;
import com.javable.daleks.models.Position;
import javafx.scene.image.Image;

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
    public void createCollision(GameMap map, ObjectBase other, boolean inWalk) {

    }

    @Override
    public void collide(GameMap map, Player other, boolean inWalk) {
        throw new IllegalStateException("Player can't collide with player");
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

    public boolean canMove(Position newPosition) {
        if(abs(newPosition.x - position.x) > 1 || abs(newPosition.y - position.y) > 1)
            return false;

        return newPosition != position;
    }

    private void gameOver() {
        ViewManager.setScene(Settings.GameOverView);
    }
}
