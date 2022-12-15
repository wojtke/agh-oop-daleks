package com.javable.daleks.enums;

import com.javable.daleks.models.Position;

public enum EDirection {
    Top, TopRight, Right, BottomRight, Bottom, BottomLeft, Left, TopLeft;

    public Position toVector() {
        return switch (this) {
            case Top         -> new Position( 0, -1);
            case TopRight    -> new Position( 1, -1);
            case Right       -> new Position( 1,  0);
            case BottomRight -> new Position( 1,  1);
            case Bottom      -> new Position( 0,  1);
            case BottomLeft  -> new Position(-1,  1);
            case Left        -> new Position(-1,  0);
            case TopLeft     -> new Position(-1, -1);
        };
    }
}
