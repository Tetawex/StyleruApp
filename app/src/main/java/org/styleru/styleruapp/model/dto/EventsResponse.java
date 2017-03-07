package org.styleru.styleruapp.model.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.styleru.styleruapp.view.EventsView;

import java.util.List;

/**
 * Created by tetawex on 06.03.17.
 */

public class EventsResponse {
    @SerializedName("data")
    @Expose
    private List<EventsItem> data;

    public List<EventsItem> getData() {
        return data;
    }

    public void setData(List<EventsItem> data) {
        this.data = data;
    }
}
