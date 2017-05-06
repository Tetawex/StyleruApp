package org.styleru.styleruapp.presenter;

import org.styleru.styleruapp.model.ProfileModel;
import org.styleru.styleruapp.model.ProfileModelImpl;
import org.styleru.styleruapp.model.dto.ProfileRequest;
import org.styleru.styleruapp.view.ProfileProjectsView;
import org.styleru.styleruapp.view.ProfileView;

import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;

/**
 * Created by Пользователь on 30.04.2017.
 */

public class ProfilePresenterImpl implements ProfilePresenter {
    //TODO: заменить инъекцию через конструктор инъекцией дагером
    private ProfileView view;
    private ProfileModel model;
    ProfileRequest request;
    private Disposable disposable= Disposables.empty();

    private int currentId=0;

    public ProfilePresenterImpl(ProfileView view) {
        this.view=view;
        this.model=new ProfileModelImpl();
    }

    @Override
    public void onProfileCreate(String token, int user_id) {
        disposable = model.getData(request)
                .subscribe(response ->
                                view.appendData(response),
                        throwable -> view.showError(throwable),
                        () -> {
                            if(!disposable.isDisposed()) {
                                disposable.dispose();
                            }
                        });

    }

    @Override
    public void onStop() {

    }


}
