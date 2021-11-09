package com.example.finalproject.dto;

public class Result {
    private boolean result;
    private String userLogin;

    public Result() {
    }

    public Result(boolean result) {
        this.result = result;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }
}
