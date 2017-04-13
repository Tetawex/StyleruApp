package org.styleru.styleruapp.model.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.styleru.styleruapp.model.dto.support.Settings;

import java.util.List;

/**
 * Created by Tetawex on 31.03.2017.
 */

public class SettingsUploadRequest {
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("university_ids")
    @Expose
    private List<Integer> universityIds;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<Integer> getUniversityIds() {
        return universityIds;
    }

    public void setUniversityIds(List<Integer> universityIds) {
        this.universityIds = universityIds;
    }
}
