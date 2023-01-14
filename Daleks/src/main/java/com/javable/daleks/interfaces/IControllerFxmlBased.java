package com.javable.daleks.interfaces;

import com.javable.daleks.logic.ViewManager;

import java.io.IOException;

public interface IControllerFxmlBased extends IController {
    String getViewPath();

    default void initView() throws IOException {
        ViewManager.setScene(getViewPath());
    }
}
