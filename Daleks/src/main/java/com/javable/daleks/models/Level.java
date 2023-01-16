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
    public final Position[] daleksPositions;
    public final boolean isCampaign;

    public Level(GameMap map){
        isCampaign = false;
        campainOrder = -1;
        this.gridSize = map.getGridSize();
        this.levelName = "Custom";
        playerPosition = map.getPlayer().getPosition();
        daleksPositions = new Position[map.getDaleks().size()];
        for (int i = 0; i < map.getDaleks().size(); i++)
            this.daleksPositions[i] = map.getDaleks().get(i).position;

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
    }

    @Provides
    public Level ProvideGameMapSettings() {
        return this;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public int getTeleportersCount() {
        return 3;
    }
    public int getAttractorsCount() {
        return 3;
    }
}
