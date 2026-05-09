package com.alvarohdez.econocom.dto;

import java.time.LocalDateTime;

public class ExceptionResponse {

    private String message;
    private int code;
    private String path;
    private LocalDateTime time;

    public ExceptionResponse(String message, int code, String path) {
        this.message = message;
        this.code = code;
        this.path = path;
        this.time = LocalDateTime.now();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

}
