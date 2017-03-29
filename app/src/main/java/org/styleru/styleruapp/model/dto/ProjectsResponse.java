package org.styleru.styleruapp.model.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by tetawex on 29.03.17.
 */

public class ProjectsResponse {
    @SerializedName("data")
    @Expose
    private List<ProjectsItem> data = null;

    public List<ProjectsItem> getData() {
        return data;
    }

    public void setData(List<ProjectsItem> data) {
        this.data = data;
    }

}
