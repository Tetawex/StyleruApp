package org.styleru.styleruapp.model;

import org.styleru.styleruapp.model.api.ApiService;
import org.styleru.styleruapp.model.cache.Singletons;
import org.styleru.styleruapp.model.dto.ProfileRequest;
import org.styleru.styleruapp.model.dto.ProfileProjectsResponse;
import org.styleru.styleruapp.model.dto.ProfileResponse;
import org.styleru.styleruapp.model.dto.ProjectsRequest;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Пользователь on 07.04.2017.
 */

public class ProfileModelImpl implements ProfileModel {
    private ApiService apiService;
    private String authToken;

    public ProfileModelImpl() {
        apiService = Singletons.getApiService();
        authToken = Singletons.getPreferencesManager().getAuthToken();
    }

    @Override
    public Observable<ProfileResponse> getData(int id) {
        ProfileRequest request = new ProfileRequest(authToken, id);
        if (id < 0)
            request.setUser_id(null);
        return apiService.getApiInterface()
                .getSingleProfileFull(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}