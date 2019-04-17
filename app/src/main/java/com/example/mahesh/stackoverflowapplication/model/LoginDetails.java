package com.example.mahesh.stackoverflowapplication.model;

import com.google.gson.annotations.SerializedName;

public class LoginDetails {
    @SerializedName("access_token")
    private String accessToken;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }


    @Override
    public String toString() {
        return "LoginDetails{" +
                "accessToken='" + accessToken + '\'' +
                '}';
    }
}
