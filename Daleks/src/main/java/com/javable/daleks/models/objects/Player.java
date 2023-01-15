package com.javable.daleks.models.objects;

import com.javable.daleks.Settings;
import com.javable.daleks.enums.EObjectType;
import com.javable.daleks.logic.ViewManager;
import com.javable.daleks.models.GameMap;
import com.javable.daleks.models.Position;

import static java.lang.Math.abs;

public class Player extends ObjectBase {

    public Player(Position position) {
        super(position, EObjectType.PLAYER);
    }

    public boolean CanMove(Position newPosition) {
        if(abs(newPosition.x - Position.x) > 1 || abs(newPosition.y - Position.y) > 1)
            return false;

        return newPosition != Position;
    }

    @Override
    public void Collide(GameMap map, ObjectBase other, boolean inWalk) {
        ViewManager.SetScene(Settings.GameOverView);
    }
}
