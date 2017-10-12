package org.styleru.styleruapp.model.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginRequest {
    @SerializedName("user_email")
    @Expose
    private String userEmail;

    @SerializedName("user_password")
    @Expose
    private String userPassword;


    public void setOnesignalUserId(String onesignalUserId) {
        this.onesignalUserId = onesignalUserId;
    }

    @SerializedName("guid")
    @Expose
    private String onesignalUserId;

    public LoginRequest(String userEmail, String userPassword, String onesignalUserId) {
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.onesignalUserId = onesignalUserId;
    }

    public LoginRequest(String userEmail, String userPassword) {
        this.userEmail = userEmail;
        this.userPassword = userPassword;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public String getOnesignalUserId() {
        return onesignalUserId;
    }
}
