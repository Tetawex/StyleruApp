package org.styleru.styleruapp.presenter;


import org.styleru.styleruapp.model.ProfileModel;
import org.styleru.styleruapp.model.ProfileModelImpl;
import org.styleru.styleruapp.view.ProfileProjectsView;

import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;

/**
 * Created by Пользователь on 03.04.2017.
 */

public class ProfileProjectsPresenterImpl implements ProfileProjectsPresenter {
    //TODO: заменить инъекцию через конструктор инъекцией дагером
    private ProfileProjectsView view;
    private ProfileModel model;

    private Disposable disposable= Disposables.empty();

    private int currentId=0;

    public ProfileProjectsPresenterImpl(ProfileProjectsView view) {
        this.view=view;
        //TODO: заменить тестовую модель на настоящую, когда сделают api
        this.model=new ProfileModelImpl();
    }

//    @Override
//    public void onDataAppend(String token, int user_id) {
//        disposable = model.getData(token,user_id)
//                .subscribe(response -> view.appendData(response.getData()),
//                        throwable -> view.showError(throwable),
//                        () -> {
//                            if(!disposable.isDisposed()) {
//                                disposable.dispose();
//                            }
//                        });
//        currentId+=batchSize;
//    }

    @Override
    public void onDataAppend(int offset, int batchSize) {

    }

    @Override
    public void onDataUpdate(int batchSize) {

    }

//    @Override
//    public void onDataUpdate(int batchSize) {
//        currentId=0;
//        if(!disposable.isDisposed()) {
//            disposable.dispose();
//        }
//        disposable = model.getData(currentId,batchSize)
//                .subscribe(response ->
//                        {
//                            view.stopProgressBar();
//                            view.setData(response.getData());
//                            view.onDataUpdated();
//                        },
//                        throwable ->
//                        {
//                            view.stopProgressBar();
//                            view.showError(throwable);
//                        },
//                        () -> {
//                            if(!disposable.isDisposed()) {
//                                disposable.dispose();
//                            }
//                        });
//        currentId+=batchSize;
//    }

    @Override
    public void onStop() {

    }
}
