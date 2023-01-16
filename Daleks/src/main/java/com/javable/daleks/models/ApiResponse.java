package com.javable.daleks.models;

import org.json.JSONObject;

public class ApiResponse {
    private final int code;
    private final String description;

    public ApiResponse(JSONObject jsonObject) {
        code = jsonObject.getInt("code");
        description = jsonObject.getString("description");
    }

    public int getCode() {
        return code;
    }
    public String getDescription() {
        return description;
    }
}
