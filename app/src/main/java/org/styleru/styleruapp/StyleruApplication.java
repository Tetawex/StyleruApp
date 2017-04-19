package org.styleru.styleruapp;

import android.app.Application;

import com.onesignal.OneSignal;

import org.styleru.styleruapp.model.api.ApiService;
import org.styleru.styleruapp.model.cache.PreferencesManager;
import org.styleru.styleruapp.model.cache.PreferencesManagerImpl;
import org.styleru.styleruapp.model.cache.Singletons;
import org.styleru.styleruapp.model.cache.UserInfo;
import org.styleru.styleruapp.util.OneSignalNotificationOpenedHandler;

/**
 * Created by Tetawex on 31.03.2017.
 */

public class StyleruApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        OneSignal
                .startInit(this)
                .autoPromptLocation(false)
                .setNotificationOpenedHandler(
                        new OneSignalNotificationOpenedHandler(this))
                .init();
        Singletons.setPreferencesManager(new PreferencesManagerImpl(getBaseContext()));
        Singletons.setApiService(new ApiService());
    }
}
