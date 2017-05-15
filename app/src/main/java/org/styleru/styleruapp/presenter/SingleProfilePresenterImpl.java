package org.styleru.styleruapp.presenter;

import android.util.Log;

import org.styleru.styleruapp.model.SingleProfileModel;
import org.styleru.styleruapp.model.SingleProfileModelImpl;
import org.styleru.styleruapp.model.dto.SingleProfileRequest;
import org.styleru.styleruapp.view.SingleProfileView;

import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;

/**
 * Created by Пользователь on 07.04.2017.
 */

public class SingleProfilePresenterImpl implements SingleProfilePresenter {
    private SingleProfileView view;
    private SingleProfileModel model;

    private Disposable disposable= Disposables.empty();

    public SingleProfilePresenterImpl(SingleProfileView view) {
        this.view=view;
        model=new SingleProfileModelImpl();
    }

    @Override
    public void onStop() {

    }

    @Override
    public void onSingleProfileAppend(String token,int id) {
        disposable = model.getSingleProfile(new SingleProfileRequest(token,id))
                .subscribe(response ->
                        {
                            view.switchToMainPage();
                            view.
                            mail = response.getEmail();
                            phone= response.getPhone();
                            name = response.getFirst_name();
                            surname= response.getLast_name();
                            image = response.getImage_url();
                        },
                        throwable ->
                        {
                            view.showError(throwable);
                        },
                        () -> {
                            if(!disposable.isDisposed()) {
                                disposable.dispose();
                            }
                        });
    }

    }

