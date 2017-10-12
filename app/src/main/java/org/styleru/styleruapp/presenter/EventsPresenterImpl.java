package org.styleru.styleruapp.presenter;

import android.util.Log;

import org.styleru.styleruapp.model.EventsModel;
import org.styleru.styleruapp.model.EventsModelImpl;
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

    private Disposable disposable = Disposables.empty();
    private Disposable secondaryDisposable = Disposables.empty();

    private int currentId = 0;

    public EventsPresenterImpl(EventsView view) {
        this.view = view;
        //TODO: заменить тестовую модель на настоящую, когда сделают api
        this.model = new EventsModelImpl();
    }

    @Override
    public void onDataAppend(int offset, int batchSize) {
        currentId = offset;
        disposable = model.getData(currentId, batchSize)
                .subscribe(response -> view.appendData(response.getData()),
                        throwable -> view.showError(throwable),
                        () -> {
                            if (!disposable.isDisposed()) {
                                disposable.dispose();
                            }
                        });
        currentId += batchSize;
    }

    @Override
    public void onDataUpdate(int batchSize) {
        currentId = 0;
        if (!disposable.isDisposed()) {
            disposable.dispose();
        }
        disposable = model.getData(currentId, batchSize)
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
                            if (!disposable.isDisposed()) {
                                disposable.dispose();
                            }
                        });
        currentId += batchSize;
    }

    @Override
    public void onEventStateChange(int id) {
        secondaryDisposable = model.getChangedState(id).subscribe(eventStateChangeResponse ->
                {
                    view.changeEventStateSuccess(id, eventStateChangeResponse.getState());
                },
                throwable ->
                {
                    view.changeEventStateFail(id);
                    view.showError(throwable);
                    Log.getStackTraceString(throwable);
                },
                () -> {
                    if (!secondaryDisposable.isDisposed()) {
                        secondaryDisposable.dispose();
                    }
                });

    }

    @Override
    public void onStop() {

    }
}
