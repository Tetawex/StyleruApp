package org.styleru.styleruapp.presenter;

import android.util.Log;

import org.styleru.styleruapp.model.EventsModel;
import org.styleru.styleruapp.model.ProjectsModel;
import org.styleru.styleruapp.model.TestProjectsModelImpl;
import org.styleru.styleruapp.model.dto.EventsItem;
import org.styleru.styleruapp.model.dto.EventsRequest;
import org.styleru.styleruapp.model.dto.EventsResponse;
import org.styleru.styleruapp.model.dto.ProjectsRequest;
import org.styleru.styleruapp.view.EventsView;
import org.styleru.styleruapp.view.ProjectsView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;

/**
 * Created by tetawex on 29.03.17.
 */

public class ProjectsPresenterImpl implements ProjectsPresenter {
    //TODO: заменить инъекцию через конструктор инъекцией дагером
    private ProjectsView view;
    private ProjectsModel model;

    private Disposable disposable= Disposables.empty();

    private int currentId=0;
    private int filterMode=0;

    public ProjectsPresenterImpl(ProjectsView view) {
        this.view=view;
        //TODO: заменить тестовую модель на настоящую, когда сделают api
        this.model=new TestProjectsModelImpl();
    }

    @Override
    public void onProjectsAppend(int offset, int batchSize, String requestString) {
        currentId=offset;
        disposable = model.getData(new ProjectsRequest("87vg430f7g237fg283f",batchSize,
                currentId,requestString,filterMode))
                // .flatMap(Observable::from)
                //.toList()
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
    public void onProjectsUpdate(int batchSize, String requestString) {
        currentId=0;
        disposable = model.getData(new ProjectsRequest("87vg430f7g237fg283f",batchSize,
                currentId,requestString,filterMode))
                //.toList()
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
    public void onSetFilterMode(int filterMode) {
        this.filterMode=filterMode;
    }

    @Override
    public void onStop() {

    }

}
