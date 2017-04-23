package org.styleru.styleruapp.view;

import org.styleru.styleruapp.model.dto.SettingsDataModel;

/**
 * Created by Tetawex on 14.04.2017.
 */

public interface SettingsView extends View{
    void setData(SettingsDataModel data);
    void switchToLoginPage();
    void notifySyncState(int state);
}
