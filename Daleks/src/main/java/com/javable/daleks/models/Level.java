package com.javable.daleks.models;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import org.json.JSONArray;
import org.json.JSONObject;

public class Level extends AbstractModule {
    private final int campaignOrder, gridSize;
    private final Position playerPosition;

    public int getCampaignOrder() {
        return campaignOrder;
    }

    public int getGridSize() {
        return gridSize;
    }

    public Position getPlayerPosition() {
        return playerPosition;
    }

    public String getLevelName() {
        return levelName;
    }

    public Position[] getDaleksPositions() {
        return daleksPositions;
    }

    public int getDaleksCount() {
        return daleksPositions.length;
    }
    public boolean isCampaign() {
        return isCampaign;
    }

    public String levelName;

    public Position[] getAttractorsPositions() {
        return attractorsPositions;
    }

    public Position[] getTeleportersPositions() {
        return teleportersPositions;
    }

    final Position[] daleksPositions, attractorsPositions, teleportersPositions;
    public final boolean isCampaign;

    public Level(int gridSize, int daleksCount, int attractorsCount, int teleportersCount, String levelName){
        isCampaign = false;
        campaignOrder = -1;
        this.gridSize = gridSize;
        this.levelName = levelName;
        playerPosition = new Position(gridSize);

        daleksPositions = new Position[daleksCount];
        for (int i = 0; i < daleksCount; i++)
            this.daleksPositions[i] = new Position(daleksCount);

        attractorsPositions = new Position[attractorsCount];
        for (int i = 0; i < attractorsCount; i++)
            this.attractorsPositions[i] = new Position(daleksCount);

        teleportersPositions = new Position[teleportersCount];
        for (int i = 0; i < teleportersCount; i++)
            this.teleportersPositions[i] = new Position(daleksCount);
    }

    public Level(
            boolean isCampainLevel,
            int campaignOrder,
            String levelName,
            int gridSize,
            Position playerPosition,
            Position[] daleksPositions,
            Position[] teleportersPositions,
            Position[] attractorsPositions)
    {
        this.isCampaign = true;
        this.campaignOrder = campaignOrder;
        this.levelName = levelName;
        this.gridSize = gridSize;
        this.playerPosition = playerPosition;
        this.daleksPositions = daleksPositions;
        this.teleportersPositions = teleportersPositions;
        this.attractorsPositions = attractorsPositions;
    }

    public static Level ParseLevelJson(JSONObject jsonObject, boolean isCampainLevel) {
        int campaignOrder = isCampainLevel ? jsonObject.getInt("campaignOrder") : -1;
        String levelName = jsonObject.getString("name");
        int gridSize = jsonObject.getInt("gridCount");
        Position playerPosition = new Position(jsonObject.getJSONObject("playerPosition"));

        JSONArray daleksJsonArray = new JSONArray(jsonObject.getJSONArray("dalekPositions"));
        Position[] daleksPositions = new Position[daleksJsonArray.length()];
        for (int i = 0; i < daleksJsonArray.length(); i++)
            daleksPositions[i] = new Position(daleksJsonArray.getJSONObject(i));

        JSONArray teleportersJsonArray = new JSONArray(jsonObject.getJSONArray("teleporterPositions"));
        Position[] teleportersPositions = new Position[teleportersJsonArray.length()];
        for (int i = 0; i < teleportersJsonArray.length(); i++)
            teleportersPositions[i] = new Position(teleportersJsonArray.getJSONObject(i));

        JSONArray attractorsJsonArray = new JSONArray(jsonObject.getJSONArray("attractorPositions"));
        Position[] attractorsPositions = new Position[attractorsJsonArray.length()];
        for (int i = 0; i < attractorsJsonArray.length(); i++)
            attractorsPositions[i] = new Position(attractorsJsonArray.getJSONObject(i));

        return new Level(isCampainLevel, campaignOrder, levelName, gridSize, playerPosition, daleksPositions, teleportersPositions, attractorsPositions);
    }

    @Provides
    public Level ProvideGameMapSettings() {
        return this;
    }

    public int getTeleportersCount() {
        return teleportersPositions.length;
    }
    public int getAttractorsCount() {
        return attractorsPositions.length;
    }
}
