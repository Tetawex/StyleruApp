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
    private ProfileItem data;

    public ProfileItem getData() {
        return data;
    }

    public void setData(ProfileItem data) {
        this.data = data;
    }
}