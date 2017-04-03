package org.styleru.styleruapp.presenter;

import org.styleru.styleruapp.model.ProfileProjectsModel;
import org.styleru.styleruapp.model.TestProfileProjectsModelImpl;
import org.styleru.styleruapp.model.dto.ProfileProjectsRequest;
import org.styleru.styleruapp.view.ProfileProjectsView;

import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;

/**
 * Created by Пользователь on 03.04.2017.
 */

public class ProfileProjectsPresenterImpl implements ProfileProjectsPresenter {
    //TODO: заменить инъекцию через конструктор инъекцией дагером
    private ProfileProjectsView view;
    private ProfileProjectsModel model;

    private Disposable disposable= Disposables.empty();

    private int currentId=0;

    public ProfileProjectsPresenterImpl(ProfileProjectsView view) {
        this.view=view;
        //TODO: заменить тестовую модель на настоящую, когда сделают api
        this.model=new TestProfileProjectsModelImpl();
    }

    @Override
    public void onProfileProjectsAppend(int offset, int batchSize) {
        currentId=offset;
        disposable = model.getData(new ProfileProjectsRequest("87vg430f7g237fg283f",batchSize,currentId))
                .subscribe(response -> view.appendData(response.getData()),
                        throwable -> view.showError(throwable),
                        () -> {
                            if(!disposable.isDisposed()) {
                                disposable.dispose();
                            }
                        });
        currentId+=batchSize;
    }

    @Override
    public void onProfileProjectsUpdate(int batchSize) {
        currentId=0;
        disposable = model.getData(new ProfileProjectsRequest("87vg430f7g237fg283f",batchSize,currentId))
                .subscribe(response ->
                        {
                            view.stopProgressBar();
                            view.setData(response.getData());
                            view.onDataUpdated();
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
        currentId+=batchSize;
    }
    @Override
    public void onProfileProjectsStatusChange(int id, boolean desiredStatus) {

    }

    @Override
    public void onStop() {

    }
}

