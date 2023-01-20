package com.javable.daleks.api;


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
    private int gridCount;
    @NotNull
    private Position[] dalekPositions, teleporterPositions, attractorPositions;
    @NotNull
    private Position playerPosition;

    private boolean isCampaign;
    private int campaignOrder;

    public Level(String id, String name, int gridCount, Position[] dalekPositions, Position[] teleporterPositions, Position[] attractorPositions, Position playerPosition, boolean isCampaign, int campaignOrder) {
        this.id = id;
        this.name = name;
        this.gridCount = gridCount;
        this.dalekPositions = dalekPositions;
        this.teleporterPositions = teleporterPositions;
        this.attractorPositions = attractorPositions;
        this.playerPosition = playerPosition;
        this.isCampaign = isCampaign;
        this.campaignOrder = campaignOrder;
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

    public int getGridCount() {
        return gridCount;
    }

    public void setGridCount(int gridCount) {
        this.gridCount = gridCount;
    }

    public Position[] getDalekPositions() {
        return dalekPositions;
    }

    public void setDalekPositions(Position[] dalekPositions) {
        this.dalekPositions = dalekPositions;
    }

    public Position[] getTeleporterPositions() {
        return teleporterPositions;
    }

    public void setTeleporterPositions(Position[] teleporterPositions) {
        this.teleporterPositions = teleporterPositions;
    }

    public Position[] getAttractorPositions() {
        return attractorPositions;
    }

    public void setAttractorPositions(Position[] attractorPositions) {
        this.attractorPositions = attractorPositions;
    }

    public Position getPlayerPosition() {
        return playerPosition;
    }

    public void setPlayerPosition(Position playerPosition) {
        this.playerPosition = playerPosition;
    }

    public boolean isCampaign() {
        return isCampaign;
    }

    public void setCampaign(boolean campaign) {
        isCampaign = campaign;
    }

    public int getCampaignOrder() {
        return campaignOrder;
    }

    public void setCampaignOrder(int campaignOrder) {
        this.campaignOrder = campaignOrder;
    }
}
