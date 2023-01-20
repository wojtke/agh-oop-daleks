package com.javable.daleks.models.objects.powersUps;

import com.javable.daleks.models.GameMap;
import com.javable.daleks.models.Position;
import com.javable.daleks.models.objects.ObjectBase;

public abstract class PowerUp extends ObjectBase {

    public PowerUp(Position position) {
        super(position);
    }



    @Override
    public void createCollision(GameMap map, ObjectBase other, boolean inWalk) {
        other.collide(map, this, inWalk);
    }

}
