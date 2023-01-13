package com.javable.daleks.enums;

import com.javable.daleks.models.Position;

public enum EDirection {
    TOP(new Position( 0, -1)),
    TOP_RIGHT(new Position( 1, -1)),
    RIGHT(new Position( 1,  0)),
    BOTTOM_RIGHT(new Position( 1,  1)),
    BOTTOM (new Position( 0,  1)),
    BOTTOM_LEFT ( new Position(-1,  1)),
    LEFT (new Position(-1,  0)),
    TOP_LEFT (new Position(-1, -1));

    private final Position vector;

    EDirection(Position position) {
        vector = position;
    }

    public Position toVector() {
        return vector;
    }
}
