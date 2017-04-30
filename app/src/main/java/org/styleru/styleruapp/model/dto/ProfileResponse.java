package org.styleru.styleruapp.model.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Пользователь on 30.04.2017.
 */

public class ProfileResponse {
    @Expose
    @SerializedName("data")
    private List< ProfileItem> data;

    public List< ProfileItem> getData() {
        return data;
    }

    public void setData(List< ProfileItem> data) {
        this.data = data;
    }
}