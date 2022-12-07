package com.javable.daleks.interfaces;

import com.javable.daleks.logic.ViewHelper;

import java.io.IOException;

public interface iController {
    String GetViewPath();
    
    default void InitView() throws IOException {
        ViewHelper.SetScene(GetViewPath());
    }
}
