package org.styleru.styleruapp.presenter;

import android.util.Log;

import org.styleru.styleruapp.model.EventsModel;
import org.styleru.styleruapp.model.TestEventsModelImpl;
import org.styleru.styleruapp.model.dto.EventsItem;
import org.styleru.styleruapp.model.dto.EventsRequest;
import org.styleru.styleruapp.model.dto.EventsResponse;
import org.styleru.styleruapp.view.EventsView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;

/**
 * Created by tetawex on 06.03.17.
 */

public class EventsPresenterImpl implements EventsPresenter {
    //TODO: заменить инъекцию через конструктор инъекцией дагером
    private EventsView view;
    private EventsModel model;

    private Disposable disposable= Disposables.empty();

    private int currentId=0;

    public EventsPresenterImpl(EventsView view) {
        this.view=view;
        //TODO: заменить тестовую модель на настоящую, когда сделают api
        this.model=new TestEventsModelImpl();
    }

    @Override
    public void onEventsAppend(int offset, int batchSize) {
        currentId=offset;
        disposable = model.getData(new EventsRequest("87vg430f7g237fg283f",batchSize,currentId))
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
    public void onEventsUpdate(int batchSize) {
        currentId=0;
        disposable = model.getData(new EventsRequest("87vg430f7g237fg283f",batchSize,currentId))
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
    public void onEventStatusChange(int id, boolean desiredStatus) {

    }

    @Override
    public void onStop() {

    }
}
