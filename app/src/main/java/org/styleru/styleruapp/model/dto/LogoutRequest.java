package org.styleru.styleruapp.model.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LogoutRequest {
    public void setOnesignalUserId(String onesignalUserId) {
        this.onesignalUserId = onesignalUserId;
    }

    @SerializedName("guid")
    @Expose
    private String onesignalUserId;

    public LogoutRequest(String onesignalUserId) {
        this.onesignalUserId =onesignalUserId;
    }

    public String getOnesignalUserId() {
        return onesignalUserId;
    }
}
