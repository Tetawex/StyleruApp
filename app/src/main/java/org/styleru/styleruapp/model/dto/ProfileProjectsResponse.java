package org.styleru.styleruapp.model.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Пользователь on 03.04.2017.
 */

public class ProfileProjectsResponse {
    @Expose
    @SerializedName("data")
    private List<SingleProfileItem> data;

    public List<SingleProfileItem> getData() {
        return data;
    }

    public void setData(List<SingleProfileItem> data) {
        this.data = data;
    }
}
