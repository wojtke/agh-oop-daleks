package com.javable.daleks.models;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Random;

public class Level extends AbstractModule {
    public final int CampaignOrder, GridCount;
    public final Position PlayerPosition;

    public int getCampaignOrder() {
        return CampaignOrder;
    }

    public int getGridCount() {
        return GridCount;
    }

    public Position getPlayerPosition() {
        return PlayerPosition;
    }

    public String getLevelName() {
        return LevelName;
    }

    public Position[] getDaleksPositions() {
        return DaleksPositions;
    }

    public boolean isCampaignLv() {
        return IsCampaignLv;
    }

    public final String LevelName;
    public final Position[] DaleksPositions;
    public final boolean IsCampaignLv;

    public Level(int gridCount, int daleksCount, String levelName) {
        IsCampaignLv = false;
        CampaignOrder = -1;
        GridCount = gridCount;
        LevelName = levelName;
        PlayerPosition = new Position(gridCount / 2, gridCount / 2);
        DaleksPositions = new Position[daleksCount];
        Random rand = new Random();
        for (int i = 0; i < daleksCount; i++)
            this.DaleksPositions[i] = new Position(daleksCount, rand);
    }

    public Level(JSONObject jsonObject)
    {
        IsCampaignLv = true;
        CampaignOrder = jsonObject.getInt("lvId");
        LevelName = jsonObject.getString("lvName");
        GridCount = jsonObject.getInt("gridCount");
        PlayerPosition = new Position(jsonObject.getJSONObject("player"));

        JSONArray daleksJsonArray = new JSONArray(jsonObject.getJSONArray("daleks"));
        DaleksPositions = new Position[daleksJsonArray.length()];

        for (int i = 0; i < daleksJsonArray.length(); i++)
            DaleksPositions[i] = new Position(daleksJsonArray.getJSONObject(i));
    }

    @Provides
    public Level ProvideGameMapSettings() {
        return this;
    }
}
