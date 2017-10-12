package org.styleru.styleruapp.presenter;

import org.styleru.styleruapp.model.ProjectsModel;
import org.styleru.styleruapp.model.ProjectsModelImpl;
import org.styleru.styleruapp.model.dto.support.PeopleFilter;
import org.styleru.styleruapp.model.dto.support.ProjectsFilter;
import org.styleru.styleruapp.util.ErrorListener;
import org.styleru.styleruapp.view.PeopleView;
import org.styleru.styleruapp.view.ProjectsView;

import java.util.ArrayList;

import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;

/**
 * Created by tetawex on 29.03.17.
 */

public class ProjectsPresenterImpl implements ProjectsPresenter {
    //TODO: заменить инъекцию через конструктор инъекцией дагером
    public static final int BATCH_SIZE = 30;

    private ProjectsView view;
    private ProjectsModel model;

    private Disposable disposable = Disposables.empty();

    private int currentId = 0;

    public ProjectsPresenterImpl(ProjectsView view) {
        this.view = view;
        this.model = new ProjectsModelImpl();
        model.setDataChangedListener(() -> {
            if (currentId == 0) {
                onDataUpdate(BATCH_SIZE);
                view.onDataUpdated();
            }
        });
        model.setErrorListener((throwable) ->
        {
            view.showError(throwable);
            view.stopProgressBar();
        });
    }

    @Override
    public void onDataAppend(int offset, int batchSize) {
        currentId = offset;
        disposable = model.getData(batchSize, currentId)
                // .flatMap(Observable::from)
                //.toList()
                .subscribe(response -> view.appendData(response),
                        throwable -> view.showError(throwable),
                        () -> {
                            if (!disposable.isDisposed()) {
                                disposable.dispose();
                            }
                        });
        currentId += batchSize;
    }

    @Override
    public void onModelUpdateCachedData() {
        model.updateCachedData();
        view.setData(new ArrayList<>());
        view.startProgressBar();
        currentId = 0;
    }

    @Override
    public void onDataUpdate(int batchSize) {
        currentId = 0;
        disposable = model.getData(batchSize, currentId)
                //.toList()
                .subscribe(response ->
                        {
                            view.stopProgressBar();
                            view.setData(response);
                            view.onDataUpdated();
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
        currentId += batchSize;
    }

    @Override
    public void onSetFilter(ProjectsFilter filter) {
        model.setFilter(filter);
        view.setData(new ArrayList<>());
        view.startProgressBar();
        onDataUpdate(BATCH_SIZE);
        currentId = 0;
    }

    @Override
    public void onSetRequestString(String requestString) {
        model.setRequestString(requestString);
        view.setData(new ArrayList<>());
        view.startProgressBar();
        onDataUpdate(BATCH_SIZE);
        currentId = 0;
    }

    @Override
    public void onStop() {

    }

}
