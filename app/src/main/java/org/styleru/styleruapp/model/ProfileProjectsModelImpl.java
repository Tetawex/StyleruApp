package org.styleru.styleruapp.model;

import org.styleru.styleruapp.model.api.ApiService;
import org.styleru.styleruapp.model.cache.Singletons;
import org.styleru.styleruapp.model.dto.DepartmentsRequest;
import org.styleru.styleruapp.model.dto.DepartmentsResponse;
import org.styleru.styleruapp.model.dto.ProfileProjectsRequest;
import org.styleru.styleruapp.model.dto.ProfileProjectsResponse;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

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
    public Observable<ProfileProjectsResponse> getData(int offset, int batchSize) {
        return apiService.getApiInterface()
                .getProfileProjects(new ProfileProjectsRequest(authToken,batchSize,offset))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}