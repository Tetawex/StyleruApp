package org.styleru.styleruapp.model.cache;

/**
 * Created by Tetawex on 31.03.2017.
 */

public class Singletons {
    private static UserInfo userInfo=null;
    private static PreferencesManager preferencesManager=null;

    public static UserInfo getUserInfo() {
        return userInfo;
    }

    public static void setUserInfo(UserInfo userInfo) {
        Singletons.userInfo = userInfo;
    }

    public static PreferencesManager getPreferencesManager() {
        return preferencesManager;
    }

    public static void setPreferencesManager(PreferencesManager preferencesManager) {
        Singletons.preferencesManager = preferencesManager;
    }
}
