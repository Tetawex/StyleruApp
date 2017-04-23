package org.styleru.styleruapp.model;

import org.styleru.styleruapp.model.api.ApiService;
import org.styleru.styleruapp.model.cache.Singletons;
import org.styleru.styleruapp.model.dto.LogoutRequest;
import org.styleru.styleruapp.model.dto.SettingsDownloadRequest;
import org.styleru.styleruapp.model.dto.SettingsDownloadResponse;
import org.styleru.styleruapp.model.dto.SettingsUploadRequest;
import org.styleru.styleruapp.model.dto.SettingsUploadResponse;
import org.styleru.styleruapp.model.dto.support.Settings;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Tetawex on 14.04.2017.
 */

public class SettingsModelImpl implements SettingsModel {
    private ApiService service;
    private String authToken;

    public SettingsModelImpl() {
        this.service = Singletons.getApiService();
        this.authToken = Singletons.getPreferencesManager().getAuthToken();
    }

    @Override
    public Observable<SettingsDownloadResponse> getData() {
        return service.getApiInterface()
                .getSettingsModel(new SettingsDownloadRequest(authToken))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<SettingsUploadResponse> setData(Settings data) {
        return service.getApiInterface()
                .uploadSettings(new SettingsUploadRequest(authToken,data))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Completable logout(String onesignalUserId) {
        return service.getApiInterface()
                .logout(new LogoutRequest(onesignalUserId))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
