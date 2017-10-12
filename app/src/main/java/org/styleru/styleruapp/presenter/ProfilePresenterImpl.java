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
    private Disposable disposable = Disposables.empty();

    public ProfilePresenterImpl(ProfileView view) {
        this.view = view;
        this.model = new ProfileModelImpl();
    }

    @Override
    public void onRequestProfileData(int user_id) {
        view.startProgressBar();
        disposable = model.getData(user_id)
                .subscribe(response -> {
                            view.inflateData(response.getData());
                            view.stopProgressBar();
                        },
                        throwable -> {
                            view.showError(throwable);
                            view.stopProgressBar();
                        },
                        () -> {
                            if (!disposable.isDisposed()) {
                                disposable.dispose();
                            }
                        });

    }

    @Override
    public void onStop() {

    }


}
