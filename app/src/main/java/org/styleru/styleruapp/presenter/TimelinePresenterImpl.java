package org.styleru.styleruapp.presenter;

import android.util.Log;

import org.styleru.styleruapp.model.TestTimelineModelImpl;
import org.styleru.styleruapp.model.TimelineModel;
import org.styleru.styleruapp.model.dto.TimelineRequest;
import org.styleru.styleruapp.view.TimelineView;

import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;

/**
 * Created by Пользователь on 06.04.2017.
 */

public class TimelinePresenterImpl implements TimelinePresenter {
    //TODO: заменить инъекцию через конструктор инъекцией дагером
    private TimelineView view;
    private TimelineModel model;

    private Disposable disposable= Disposables.empty();

    private int currentId=0;

    public TimelinePresenterImpl(TimelineView view) {
        this.view=view;
        //TODO: заменить тестовую модель на настоящую, когда сделают api
        this.model=new TestTimelineModelImpl();
    }

    @Override
    public void onTimelineAppend(int offset, int batchSize) {
        currentId=offset;
        disposable = model.getData(new TimelineRequest("87vg430f7g237fg283f",batchSize,currentId))
                .subscribe(response -> view.appendData(response.getData()),
                        throwable -> view.showError(throwable),
                        () -> {
                            if(!disposable.isDisposed()) {
                                Log.d("Time","Append");
                                disposable.dispose();
                            }
                        });
        currentId+=batchSize;
    }

    @Override
    public void onTimelineUpdate(int batchSize) {
        currentId=0;
        disposable = model.getData(new TimelineRequest("87vg430f7g237fg283f",batchSize,currentId))
                .subscribe(response ->
                        {
                            view.setData(response.getData());
                            Log.d("Timeline","Update");
                            view.onDataUpdated();
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
        currentId+=batchSize;
    }
    @Override
    public void onTimelineStatusChange(int id, boolean desiredStatus) {

    }

    @Override
    public void onStop() {

    }
}

