package org.styleru.styleruapp.model.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.styleru.styleruapp.model.dto.support.University;

import java.util.List;

/**
 * Created by tetawex on 10.04.17.
 */

public class SettingsDataModel {
    @SerializedName("universities")
    @Expose
    private List<University> universitiesList;

    public List<University> getUniversitiesList() {
        return universitiesList;
    }

    public void setUniversitiesList(List<University> universitiesList) {
        this.universitiesList = universitiesList;
    }
}
