package org.styleru.styleruapp.model.dto;

import com.google.gson.annotations.SerializedName;

public class SignUpResponse {
    @SerializedName("user_id")
    private final int userId;

    @SerializedName("user_active")
    private final boolean userActive;

    @SerializedName("status_code")
    private final int statusCode;

    public SignUpResponse(int userId, boolean userActive, int statusCode) {
        this.userId = userId;
        this.userActive = userActive;
        this.statusCode = statusCode;
    }

    public int getUserId() {
        return userId;
    }

    public boolean isUserActive() {
        return userActive;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
