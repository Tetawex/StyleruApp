package org.styleru.styleruapp.model.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Пользователь on 03.04.2017.
 */

public class ProfileRequest {
    public ProfileRequest(String token, int user_id) {
        this.token = token;
        this.user_id = user_id;
    }

    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("user_id")
    @Expose
    private Integer user_id;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }
}
