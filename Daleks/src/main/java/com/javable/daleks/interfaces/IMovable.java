package com.javable.daleks.interfaces;

import com.javable.daleks.enums.EDirection;
import kotlin.NotImplementedError;

public interface IMovable {
    boolean canMove(EDirection direction);
    void move(EDirection direction);
}
