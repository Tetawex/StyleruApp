package org.styleru.styleruapp.model.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by tetawex on 30.04.17.
 */

public class VacancyRequest {
    @SerializedName("id_vacancy")
    @Expose
    private int id;
    @SerializedName("token")
    @Expose
    private String token;

    public VacancyRequest(int id, String token) {
        this.id = id;
        this.token = token;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
