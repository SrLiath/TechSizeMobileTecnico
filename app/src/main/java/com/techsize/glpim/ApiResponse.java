package com.techsize.glpim;

import com.google.gson.annotations.SerializedName;

public class ApiResponse {

    @SerializedName("session_token")
    private String sessionToken;

    @SerializedName("success")
    private boolean success;

    public String getSessionToken() {
        return sessionToken;
    }

    public boolean isSuccess() {
        return success;
    }
}
