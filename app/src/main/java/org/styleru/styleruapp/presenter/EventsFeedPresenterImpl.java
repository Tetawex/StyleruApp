package org.styleru.styleruapp.presenter;

import android.util.Log;

import org.styleru.styleruapp.model.EventsModel;
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
        currentId=offset;
        disposable = model.getData(new EventsRequest("87vg430f7g237fg283f",batchSize,currentId))
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
    public void onEventsUpdate(int batchSize) {
        currentId=0;
        disposable = model.getData(new EventsRequest("87vg430f7g237fg283f",batchSize,currentId))
                // .flatMap(Observable::from)
                //.toList()
                .subscribe(response ->
                        {
                            view.setData(response.getData());
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
    public void onEventStatusChange(int id, boolean desiredStatus) {

    }

    @Override
    public void onStop() {

    }

    /**
     * Created by tetawex on 07.03.17.
     */

    public static class TestEventsModelImpl implements EventsModel {
        private TestEventsItemFactory testEventsItemFactory;

        public TestEventsModelImpl() {
            testEventsItemFactory = new TestEventsItemFactory();
        }

        @Override
        public Observable<EventsResponse> getData(EventsRequest request) {
            if(request.getOffset()==0)
                testEventsItemFactory.reset();
            EventsResponse eventsResponse=new EventsResponse();
            List<EventsItem> list=new ArrayList<EventsItem>();
            for(int i=request.getOffset();i<request.getBatchSize()+request.getOffset();i++)
                list.add(testEventsItemFactory.generateRandomItem(i));
            eventsResponse.setData(list);
            Log.d("test",eventsResponse.getData().size()+"");
            return Observable.just(eventsResponse);
        }
        private class TestEventsItemFactory{
            private Random random=new Random(12345);
            public void reset(){
                random=new Random(12345);
            }
            private EventsItem generateRandomItem(int id){
                EventsItem item=new EventsItem();
                item.setTitle("Общая встреча организации");
                item.setSubtitle("By Аня Подображных");
                item.setId(id);

                int r=random.nextInt(5);
                if(r==1)
                    item.setImageUrl("http://1fichier.com/?zou0jfiprx");
                else if(r==2)
                    item.setImageUrl("https://1fichier.com/?knglu55wha");
                else if(r==3)
                    item.setImageUrl("https://1fichier.com/?df9sro0hk4");
                else if(r==4)
                    item.setImageUrl("https://1fichier.com/?7y42cmkp7b");
                else
                    item.setImageUrl("");
                item.setLocation("Кирпичная, 33");
                item.setDateTime("2017-"+(random.nextInt(6)+3)+"-23T"+(random.nextInt(9)+10)+":00:43.511Z");

                return item;

            }
        }
    }
}
