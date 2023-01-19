package com.javable.daleks.api;

import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;


@Document("levels")
public class Level {
    @Id
    private String id;
    @NotNull
    private String name;
    @NotNull
    @Positive
    private int mapSize;
    @NotNull
    @Size.List({
            @Size(min = 1, message = "At least one dalek is required"),
    })
    private Position[] dalekPositions;
    @NotNull
    private Position doctorPosition;

    public Level(String name, int mapSize, Position[] dalekPositions, Position doctorPosition) {
        this.name = name;
        this.mapSize = mapSize;
        this.dalekPositions = dalekPositions;
        this.doctorPosition = doctorPosition;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMapSize() {
        return mapSize;
    }

    public void setMapSize(int mapSize) {
        this.mapSize = mapSize;
    }

    public Position[] getDalekPositions() {
        return dalekPositions;
    }

    public void setDalekPositions(Position[] dalekPositions) {
        this.dalekPositions = dalekPositions;
    }

    public Position getDoctorPosition() {
        return doctorPosition;
    }

    public void setDoctorPosition(Position doctorPosition) {
        this.doctorPosition = doctorPosition;
    }
}
