package org.styleru.styleruapp.model.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.styleru.styleruapp.model.dto.support.Department;
import org.styleru.styleruapp.model.dto.support.PeopleFilter;
import org.styleru.styleruapp.model.dto.support.Subdepartment;

import java.util.List;

/**
 * Created by tetawex on 29.03.17.
 */

public class PeopleResponse {

    @SerializedName("data")
    @Expose
    private List<PeopleItem> data = null;

    public List<PeopleItem> getData() {
        return data;
    }

    public void setData(List<PeopleItem> data) {
        this.data = data;
    }
}
