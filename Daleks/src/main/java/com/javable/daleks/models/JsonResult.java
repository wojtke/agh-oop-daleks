package com.javable.daleks.models;

import org.json.JSONObject;

public class JsonResult {
    public final int Code;
    public final String Description;

    public JsonResult(JSONObject jsonObject) {
        Code = jsonObject.getInt("code");
        Description = jsonObject.getString("description");
    }
}
