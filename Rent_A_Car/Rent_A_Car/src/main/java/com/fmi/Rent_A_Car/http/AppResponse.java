package com.fmi.Rent_A_Car.http;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class AppResponse {

    private Map<String, Object> response;

    public AppResponse() {
        response = new HashMap<>();
    }

    // Метод за успешен отговор
    public static AppResponse success() {
        AppResponse appResponse = new AppResponse();
        appResponse.response.put("status", "success");
        appResponse.response.put("code", HttpStatus.OK.value());
        return appResponse;
    }

    // Метод за грешка
    public static AppResponse error() {
        AppResponse appResponse = new AppResponse();
        appResponse.response.put("status", "error");
        appResponse.response.put("code", HttpStatus.BAD_REQUEST.value());
        return appResponse;
    }

    // Метод за статус код
    public AppResponse withCode(HttpStatus code) {
        response.put("code", code.value());
        return this;
    }

    // Метод за съобщение
    public AppResponse withMessage(String message) {
        response.put("message", message);
        return this;
    }

    // Метод за данни
    public AppResponse withData(Object data) {
        response.put("data", data);
        return this;
    }

    // Метод за изграждане на отговор
    public ResponseEntity<Object> build() {
        int code = (int) response.get("code");
        return new ResponseEntity<>(response, HttpStatus.valueOf(code));
    }
}

