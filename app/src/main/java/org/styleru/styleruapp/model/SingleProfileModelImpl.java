package org.styleru.styleruapp.model;

import org.styleru.styleruapp.model.api.ApiService;
import org.styleru.styleruapp.model.cache.Singletons;
import org.styleru.styleruapp.model.dto.SingleProfileRequest;
import org.styleru.styleruapp.model.dto.SingleProfileResponse;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by Пользователь on 07.04.2017.
 */

public class SingleProfileModelImpl implements SingleProfileModel {
    private String authToken;
    private ApiService apiService;


    public SingleProfileModelImpl() {
        apiService = Singletons.getApiService();
        authToken = Singletons.getPreferencesManager().getAuthToken();
    }

    @Override
    public Observable<SingleProfileResponse> getSingleProfile(SingleProfileRequest request) {
        Observable<SingleProfileResponse> observable = apiService.getApiInterface().getSingleProfile(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        return observable;
    }

}