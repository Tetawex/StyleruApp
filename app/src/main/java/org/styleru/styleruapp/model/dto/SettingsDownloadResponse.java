package org.styleru.styleruapp.model.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.styleru.styleruapp.model.dto.support.Settings;

/**
 * Created by Tetawex on 31.03.2017.
 */

public class SettingsDownloadResponse {
    @SerializedName("settings")
    @Expose
    private SettingsDataModel settingsDataModel;

    public SettingsDataModel getSettingsDataModel() {
        return settingsDataModel;
    }

    public void setSettingsDataModel(SettingsDataModel settingsDataModel) {
        this.settingsDataModel = settingsDataModel;
    }
}
