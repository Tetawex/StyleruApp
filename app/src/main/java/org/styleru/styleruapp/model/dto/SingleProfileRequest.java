package org.styleru.styleruapp.model.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Пользователь on 05.04.2017.
 */

public class SingleProfileRequest {

    public SingleProfileRequest(String token, int id, int offset) {
        this.token = token;
        this.offset = offset;
        this.id = id;
    }

    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("offset")
    @Expose
    private int offset;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }
}
