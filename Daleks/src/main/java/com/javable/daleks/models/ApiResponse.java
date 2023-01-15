package com.javable.daleks.models;

import org.json.JSONObject;

public class ApiResponse {
    public final int Code;
    public final String Description;

    public ApiResponse(JSONObject jsonObject) {
        Code = jsonObject.getInt("code");
        Description = jsonObject.getString("description");
    }
}
