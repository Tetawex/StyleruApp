package org.styleru.styleruapp.model.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Пользователь on 03.04.2017.
 */

public class ProfileProjectsResponse {
    @SerializedName("data")
    @Expose

    private List<ProfileProjectsItem> data;

    public List<ProfileProjectsItem> getData() {
        return data;
    }

    public void setData(List<ProfileProjectsItem> data) {
        this.data = data;
    }
}
