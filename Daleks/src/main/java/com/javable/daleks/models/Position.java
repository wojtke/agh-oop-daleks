package com.javable.daleks.models;

import com.javable.daleks.enums.EDirection;

import java.util.Objects;

public class Position{
    public int x, y;

    public Position(int x, int y) {
        this.x = x; this.y = y;
    }

    public Position(Position position) {
        this.x = position.x; this.y = position.y;
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

    public void add(Position other) {
        x += other.x; y += other.y;
    }

    public static Position ToVector(EDirection direction) {
        return switch (direction) {
            case Top -> new Position(0, -1);
            case TopRight -> new Position(1, -1);
            case Right -> new Position(1, 0);
            case BottomRight -> new Position(1, 1);
            case Bottom -> new Position(0, 1);
            case BottomLeft -> new Position(-1, 1);
            case Left -> new Position(-1, 0);
            case TopLeft -> new Position(-1, -1);
        };
    }
}
