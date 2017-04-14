package org.styleru.styleruapp.model;

import org.styleru.styleruapp.model.dto.SettingsDataModel;
import org.styleru.styleruapp.model.dto.SettingsDownloadResponse;
import org.styleru.styleruapp.model.dto.SettingsUploadResponse;
import org.styleru.styleruapp.model.dto.support.Settings;

import io.reactivex.Observable;

/**
 * Created by Tetawex on 14.04.2017.
 */

public interface SettingsModel {
    Observable<SettingsDownloadResponse> getData();
    Observable<SettingsUploadResponse> setData(Settings data);
}
