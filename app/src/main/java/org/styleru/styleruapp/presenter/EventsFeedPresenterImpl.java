package org.styleru.styleruapp.presenter;

import org.styleru.styleruapp.model.EventsModel;
import org.styleru.styleruapp.model.TestEventsModelImpl;
import org.styleru.styleruapp.model.dto.EventsItem;
import org.styleru.styleruapp.model.dto.EventsRequest;
import org.styleru.styleruapp.view.EventsView;

import java.util.ArrayList;

import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;

/**
 * Created by tetawex on 06.03.17.
 */

public class EventsFeedPresenterImpl implements EventsFeedPresenter {
    //TODO: заменить инъекцию через конструктор инъекцией дагером
    private EventsView view;
    private EventsModel model;

    private Disposable disposable= Disposables.empty();

    private int currentId=0;

    public EventsFeedPresenterImpl(EventsView view) {
        this.view=view;
        //TODO: заменить тестовую модель на настоящую, когда сделают api
        this.model=new TestEventsModelImpl();
    }

    @Override
    public void onEventsAppend(int offset, int batchSize) {
        currentId+=batchSize;
        disposable = model.getData(new EventsRequest("87vg430f7g237fg283f",currentId,batchSize))
                // .flatMap(Observable::from)
                //.toList()
                .subscribe(response -> view.appendData(response.getData()),
                        throwable -> view.showError(throwable),
                        () -> {
                            if(!disposable.isDisposed()) {
                                disposable.dispose();
                            }
                        });
        view.onDataUpdated();
    }

    @Override
    public void onEventsUpdate(int batchSize) {
        currentId=0;
        view.setData(new ArrayList<EventsItem>());
        onEventsAppend(currentId,batchSize);
    }

    @Override
    public void onStop() {

    }
}
