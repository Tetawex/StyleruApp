package org.styleru.styleruapp.model.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Пользователь on 06.04.2017.
 */

public class TimelineResponse {
    @Expose
    @SerializedName("data")
    private List<TimelineItem> data;

    public List<TimelineItem> getData() {
        return data;
    }

    public void setData(List<TimelineItem> data) {
        this.data = data;
    }
}