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
    @SerializedName("id_university")
    @Expose
    private Integer universityId;

    public SettingsUploadRequest(String token,Settings settings) {
        this.token = token;
        universityId=settings.getUniversityId();
    }
}
