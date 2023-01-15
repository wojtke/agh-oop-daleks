package com.javable.daleks.models.objects;

import com.javable.daleks.enums.EObjectType;
import com.javable.daleks.models.GameMap;
import com.javable.daleks.models.Position;

public class Scrap extends ObjectBase {

    public Scrap(Position position) {
        super(position, EObjectType.SCRAP);
    }

    @Override
    public void Collide(GameMap map, ObjectBase other, boolean inWalk) { }
}
