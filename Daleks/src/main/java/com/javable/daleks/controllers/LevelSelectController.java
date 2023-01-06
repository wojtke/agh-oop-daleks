package com.javable.daleks.controllers;

import com.javable.daleks.Settings;
import com.javable.daleks.interfaces.IControllerFxmlBased;
import com.javable.daleks.logic.ViewManager;

public class LevelSelectController implements IControllerFxmlBased {
    @Override
    public String GetViewPath() {
        return Settings.LevelSelectView;
    }


    public void GoBackBtn() {
        ViewManager.SetScene(Settings.MainView);
    }
}
