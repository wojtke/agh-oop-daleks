package com.javable.daleks.models;

import com.javable.daleks.enums.EDirection;
import org.json.JSONObject;

import java.util.Objects;
import java.util.Random;

public class Position {
    public final int x;
    public final int y;
    private static final Random rand = new Random();


    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Position(int range) {
        x = rand.nextInt(range);
        y = rand.nextInt(range);
    }

    public Position(JSONObject jsonObject) {
        this(jsonObject.getInt("x"), jsonObject.getInt("y"));
    }

    @Override
    public String toString() {
        return "[" + x + ", " + y + "]";

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
    public int distanceSqr(Position other) {
        return (x-other.x)*(x-other.x) + (y-other.y)*(y-other.y);
    }
    public Position add(Position other) {
        return new Position(x + other.x, y + other.y);
    }

    public EDirection directionTo(Position other) {
        int dx = other.x - x, dy = other.y - y;

        if (dx == 0 && dy == 0)
            throw new IllegalArgumentException("Cannot calculate direction to self");

        if (dx == 0)
            return dy > 0 ? EDirection.BOTTOM : EDirection.TOP;
        if (dy == 0)
            return dx > 0 ? EDirection.RIGHT : EDirection.LEFT;
        if (dx > 0)
            return dy > 0 ? EDirection.BOTTOM_RIGHT : EDirection.TOP_RIGHT;
        return dy > 0 ? EDirection.BOTTOM_LEFT : EDirection.TOP_LEFT;
    }
}
