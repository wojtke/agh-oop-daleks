package com.javable.daleks.models;

import com.javable.daleks.enums.EDirection;

import java.util.Objects;

public class Position {
    public final int x;
    public final int y;

    public Position(int x, int y) {
        this.x = x; this.y = y;
    }

    @Override
    public String toString() {
        return '[' + x + ", " + y + ']';
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other)
            return true;
        if (other == null)
            return false;
        if (this.getClass() != other.getClass())
            return false;
        Position pos = (Position) other;
        return x == pos.x && y == pos.y;
    }

    public Position add(Position other) {
        return new Position(x + other.x, y + other.y);
    }

    public EDirection directionTo(Position other) {
        int dx = other.x - x, dy = other.y - y;

        if (dx == 0 && dy == 0)
            return null;
        if (dx == 0)
            return dy > 0 ? EDirection.Bottom : EDirection.Top;
        if (dy == 0)
            return dx > 0 ? EDirection.Right : EDirection.Left;
        if (dx > 0)
            return dy > 0 ? EDirection.BottomRight : EDirection.TopRight;
        return dy > 0 ? EDirection.BottomLeft : EDirection.TopLeft;
    }
}
