package org.styleru.styleruapp.model.cache;

import org.styleru.styleruapp.model.api.ApiInterface;
import org.styleru.styleruapp.model.api.ApiService;

/**
 * Created by Tetawex on 31.03.2017.
 */

public class Singletons {
    private static UserInfo userInfo=null;
    private static ApiService apiService=null;
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

    public static ApiService getApiService() {
        /*if(apiService==null)
            setApiService(new ApiService());*/
        return apiService;
    }

    public static void setApiService(ApiService apiService) {
        Singletons.apiService = apiService;
    }
}
