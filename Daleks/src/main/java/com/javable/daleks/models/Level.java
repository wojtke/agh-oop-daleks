package com.javable.daleks.models;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import org.json.JSONArray;
import org.json.JSONObject;

public class Level extends AbstractModule {
    private final int campainOrder, gridSize;
    private final Position playerPosition;

    public int getCampainOrder() {
        return campainOrder;
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
    public final Position[] daleksPositions, attractorsPositions, teleportersPositions;
    public final boolean isCampaign;

    public Level(int gridSize, int daleksCount, int attractorsCount, int teleportersCount, String levelName){
        isCampaign = false;
        campainOrder = -1;
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

    public Level(JSONObject jsonObject)
    {
        isCampaign = true;
        campainOrder = jsonObject.getInt("lvId");
        levelName = jsonObject.getString("lvName");
        gridSize = jsonObject.getInt("gridCount");
        playerPosition = new Position(jsonObject.getJSONObject("player"));

        JSONArray daleksJsonArray = new JSONArray(jsonObject.getJSONArray("daleks"));
        daleksPositions = new Position[daleksJsonArray.length()];
        for (int i = 0; i < daleksJsonArray.length(); i++)
            daleksPositions[i] = new Position(daleksJsonArray.getJSONObject(i));

        JSONArray teleportersJsonArray = new JSONArray(jsonObject.getJSONArray("teleporters"));
        teleportersPositions = new Position[teleportersJsonArray.length()];
        for (int i = 0; i < teleportersJsonArray.length(); i++)
            teleportersPositions[i] = new Position(teleportersJsonArray.getJSONObject(i));

        JSONArray attractorsJsonArray = new JSONArray(jsonObject.getJSONArray("attractors"));
        attractorsPositions = new Position[attractorsJsonArray.length()];
        for (int i = 0; i < attractorsJsonArray.length(); i++)
            attractorsPositions[i] = new Position(attractorsJsonArray.getJSONObject(i));
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
