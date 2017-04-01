package org.styleru.styleruapp.model.cache;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Tetawex on 31.03.2017.
 */

public class PreferencesManagerImpl implements PreferencesManager {
    public static final String PREFERENCES = "styleruapp";
    public static final String PREF_TOKEN = "token";


    private SharedPreferences sharedPreferences;

    public PreferencesManagerImpl(Context context) {
        sharedPreferences=context.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
    }
    @Override
    public String getAuthToken() {
        if(sharedPreferences.contains(PREF_TOKEN))
            return sharedPreferences.getString(PREF_TOKEN, "");
        return "";
    }

    @Override
    public void setAuthToken(String token) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PREF_TOKEN, token);
        editor.apply();
    }
}
