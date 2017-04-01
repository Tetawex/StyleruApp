package org.styleru.styleruapp.presenter;

import org.styleru.styleruapp.model.LoginModel;
import org.styleru.styleruapp.model.LoginModelImpl;
import org.styleru.styleruapp.model.TestLoginModelImpl;
import org.styleru.styleruapp.model.cache.Singletons;
import org.styleru.styleruapp.model.cache.UserInfo;
import org.styleru.styleruapp.model.dto.LoginRequest;
import org.styleru.styleruapp.model.dto.ValidateTokenRequest;
import org.styleru.styleruapp.view.LoginView;

import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;

/**
 * Created by tetawex on 06.03.17.
 */

public class LoginPresenterImpl implements LoginPresenter {
    //TODO: заменить инъекцию через конструктор инъекцией дагером
    private LoginView view;
    private LoginModel model;

    private Disposable disposable= Disposables.empty();

    public LoginPresenterImpl(LoginView view) {
        this.view=view;
        model=new LoginModelImpl();
    }

    @Override
    public void onStop() {

    }

    @Override
    public void onLogin(String email, String password) {
        view.startProgressBar();
        disposable = model.getLoginResponse(new LoginRequest(email,password))
                .subscribe(response ->
                        {
                            Singletons.setUserInfo(new UserInfo(response));
                            Singletons.getPreferencesManager().setAuthToken(response.getToken());
                            view.switchToMainPage();
                        },
                        throwable ->
                        {
                            view.stopProgressBar();
                            view.showError(throwable);
                        },
                        () -> {
                            if(!disposable.isDisposed()) {
                                disposable.dispose();
                            }
                        });
    }

    @Override
    public void onValidateToken() {
        String token=Singletons.getPreferencesManager().getAuthToken();
        if(!token.equals("")) {
            view.startProgressBar();
            disposable = model.validateToken(new ValidateTokenRequest(token))
                    .subscribe(response ->
                            {
                                Singletons.setUserInfo(new UserInfo(response));
                                view.switchToMainPage();
                            },
                            throwable ->
                            {
                                view.stopProgressBar();
                                view.showError(throwable);
                            },
                            () -> {
                                if (!disposable.isDisposed()) {
                                    disposable.dispose();
                                }
                            });
        }
    }
}
