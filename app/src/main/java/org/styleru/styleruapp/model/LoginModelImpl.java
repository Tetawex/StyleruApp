package org.styleru.styleruapp.model;

import org.styleru.styleruapp.model.api.ApiService;
import org.styleru.styleruapp.model.cache.Singletons;
import org.styleru.styleruapp.model.dto.LoginRequest;
import org.styleru.styleruapp.model.dto.LoginResponse;
import org.styleru.styleruapp.model.dto.ValidateTokenRequest;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Tetawex on 31.03.2017.
 */

public class LoginModelImpl implements LoginModel {
    private ApiService apiService;
    public LoginModelImpl(){
        apiService= Singletons.getApiService();
    }
    @Override
    public Observable<LoginResponse> getLoginResponse(LoginRequest request) {
        Observable<LoginResponse> observable =apiService.getApiInterface().login(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        return observable;
    }

    @Override
    public Observable<LoginResponse> validateToken(ValidateTokenRequest request) {
        Observable<LoginResponse> observable =apiService.getApiInterface().validateToken(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        return observable;
    }
}
