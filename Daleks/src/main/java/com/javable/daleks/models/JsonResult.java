package com.javable.daleks.models;

public class JsonResult {
    public final int Code;
    public final String Description;

    public JsonResult(int code, String description) {
        Code = code; Description = description;
    }
}
