package com.javable.daleks.models;

import org.json.JSONObject;

public class JsonResult {
    public final int code;
    public final String description;

    public JsonResult(JSONObject jsonObject) {
        code = jsonObject.getInt("code");
        description = jsonObject.getString("description");
    }
}
