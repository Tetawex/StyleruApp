package org.styleru.styleruapp.model.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Tetawex on 31.03.2017.
 */

public class DepartmentsResponse {
    @SerializedName("data")
    @Expose
    private List<DepartmentsItem> data;

    public List<DepartmentsItem> getData() {
        return data;
    }

    public void setData(List<DepartmentsItem> data) {
        this.data = data;
    }
}
