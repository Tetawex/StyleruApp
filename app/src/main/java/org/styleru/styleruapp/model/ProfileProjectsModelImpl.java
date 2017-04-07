package org.styleru.styleruapp.model;

import org.styleru.styleruapp.model.api.ApiService;
import org.styleru.styleruapp.model.cache.Singletons;
import org.styleru.styleruapp.model.dto.ProfileProjectsRequest;
import org.styleru.styleruapp.model.dto.ProfileProjectsResponse;
import org.styleru.styleruapp.model.dto.ProfileProjectsStateChangeRequest;
import org.styleru.styleruapp.model.dto.ProfileProjectsStateChangeResponse;

import io.reactivex.Observable;

/**
 * Created by Пользователь on 07.04.2017.
 */

public class ProfileProjectsModelImpl implements ProfileProjectsModel {
    private ApiService apiService;
    private String authToken;
    public ProfileProjectsModelImpl() {
        apiService= Singletons.getApiService();
        authToken=Singletons.getPreferencesManager().getAuthToken();
    }

    @Override
    public Observable<ProfileProjectsResponse> getData(ProfileProjectsRequest request) {
        return null;
    }

    @Override
    public Observable<ProfileProjectsStateChangeResponse> getChangedState(ProfileProjectsStateChangeRequest request) {
        return null;
    }
}