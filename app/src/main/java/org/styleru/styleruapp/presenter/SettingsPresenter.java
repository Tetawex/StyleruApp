package org.styleru.styleruapp.presenter;

import org.styleru.styleruapp.model.dto.support.Settings;

/**
 * Created by Tetawex on 14.04.2017.
 */

public interface SettingsPresenter {
    void onSettingsSync(Settings settings);
    void onSettingsModelDownload();
}
