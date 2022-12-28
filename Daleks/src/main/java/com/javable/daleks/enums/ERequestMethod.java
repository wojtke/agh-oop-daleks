package com.javable.daleks.enums;

public enum ERequestMethod {
    GET, POST;

    public String toString() {
        return switch (this) {
            case GET -> "GET";
            case POST -> "POST";
        };
    }
}
