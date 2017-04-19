package org.styleru.styleruapp.util;

import android.app.Application;
import android.content.Intent;

import com.onesignal.OSNotificationAction;
import com.onesignal.OSNotificationOpenResult;
import com.onesignal.OneSignal;

import org.json.JSONObject;
import org.styleru.styleruapp.view.activity.LoginActivity;

/**
 * Created by tetawex on 18.04.17.
 */

public class OneSignalNotificationOpenedHandler
        implements OneSignal.NotificationOpenedHandler {
    private Application application;
    public OneSignalNotificationOpenedHandler(Application application){
        this.application=application;
    }

    @Override
    public void notificationOpened(OSNotificationOpenResult result) {
        Intent intent = new Intent(application, LoginActivity.class);
        application.startActivity(intent);

    }
}
