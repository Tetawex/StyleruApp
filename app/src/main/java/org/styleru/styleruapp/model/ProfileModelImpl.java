package org.styleru.styleruapp.model;

import org.styleru.styleruapp.model.api.ApiService;
import org.styleru.styleruapp.model.cache.Singletons;
import org.styleru.styleruapp.model.dto.ProfileRequest;
import org.styleru.styleruapp.model.dto.ProfileProjectsResponse;
import org.styleru.styleruapp.model.dto.ProfileResponse;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Пользователь on 07.04.2017.
 */

public class ProfileModelImpl implements ProfileModel {
    private ApiService apiService;
    private String authToken;
    private int user_id = 0;
    public ProfileModelImpl() {
        apiService= Singletons.getApiService();
        authToken=Singletons.getPreferencesManager().getAuthToken();
    }
    @Override
    public Observable<ProfileResponse> getData(ProfileRequest request) {
        return apiService.getApiInterface()
                .getSingleProfileFull(new ProfileRequest(authToken,user_id))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}