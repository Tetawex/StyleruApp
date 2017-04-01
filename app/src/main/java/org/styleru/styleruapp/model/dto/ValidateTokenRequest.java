package org.styleru.styleruapp.model.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ValidateTokenRequest {
    @SerializedName("token")
    @Expose
    private final String token;

    public ValidateTokenRequest(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
