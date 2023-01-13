package com.javable.daleks.interfaces;

import com.javable.daleks.logic.ViewManager;

import java.io.IOException;

public interface IControllerFxmlBased extends IController {
    String GetViewPath();

    default void InitView() throws IOException {
        ViewManager.SetScene(GetViewPath());
    }
}
