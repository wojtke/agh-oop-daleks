package com.javable.daleks.api;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@JsonFormat(shape= JsonFormat.Shape.ARRAY)
@JsonPropertyOrder({ "x", "y" })
public class Position {
    @NotNull
    @Positive
    private int x, y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
