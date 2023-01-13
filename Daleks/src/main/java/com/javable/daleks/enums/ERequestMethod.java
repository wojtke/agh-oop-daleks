package com.javable.daleks.enums;

public enum ERequestMethod {
    GET("GET"), POST("POST");

    ERequestMethod(String value) {
        this.value = value;
    }

    private final String value;

    public String toString() {
        return value;
    }
}
