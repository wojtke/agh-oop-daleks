package com.javable.daleks.logic;

import com.javable.daleks.Settings;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class CampaignManager {
    private static int currentLvCache = ReadMaxCampaignLv();

    private static int ReadMaxCampaignLv() {
        try(BufferedReader reader = new BufferedReader(new FileReader(Settings.UserCampaign))) {
            return Integer.parseInt(reader.readLine());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void WriteMaxCampaignLv(int lv) {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(Settings.UserCampaign, false))) {
            writer.write(Integer.toString(lv));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void SetCurrentMaxLv(int value) {
        currentLvCache = value;
        WriteMaxCampaignLv(value);
    }
    public static int GetCurrentMaxLv() { return currentLvCache; }
    public static void IncrementMaxCampaignLvAfterBeatingLv(int orderOfBeatenLv) {
        if (orderOfBeatenLv == currentLvCache)
            SetCurrentMaxLv(currentLvCache + 1);
    }
    public static void ResetMaxCampaignLv() { SetCurrentMaxLv(1); }
}
