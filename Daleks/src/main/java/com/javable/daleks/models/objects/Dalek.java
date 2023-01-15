package com.javable.daleks.models.objects;

import com.javable.daleks.enums.EObjectType;
import com.javable.daleks.models.GameMap;
import com.javable.daleks.models.Position;

public class Dalek extends ObjectBase {

    public Dalek(Position position) {
        super(position, EObjectType.DALEK);
    }

    @Override
    public void Collide(GameMap map, ObjectBase other, boolean inWalk) {
        if (other.ObjectType != EObjectType.PLAYER) {
            map.RemoveDalek(this);
            if (inWalk)
                map.AddScrap(new Scrap(this.Position));
        }
    }
}
