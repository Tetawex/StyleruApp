package org.styleru.styleruapp.model;

import org.styleru.styleruapp.model.dto.EventsItem;
import org.styleru.styleruapp.model.dto.EventsRequest;
import org.styleru.styleruapp.model.dto.EventsResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import io.reactivex.Observable;

/**
 * Created by tetawex on 07.03.17.
 */

public class TestEventsModelImpl implements EventsModel {
    @Override
    public Observable<EventsResponse> getData(EventsRequest request) {
        EventsResponse eventsResponse=new EventsResponse();
        List<EventsItem> list=new ArrayList<EventsItem>();
        for(int i=0;i<request.getBatchSize();i++)
            list.add(TestEventsItemFactory.generateRandomItem(request.getOffset()+i));
        eventsResponse.setData(list);
        return Observable.just(eventsResponse);
    }
    private static class TestEventsItemFactory{
        private static EventsItem generateRandomItem(int id){
            Random random=new Random();

            EventsItem item=new EventsItem();
            item.setTitle("Общая встреча организации");
            item.setSubtitle("By Аня Подображных");
            item.setId(id);
            
            item.setImageUrl("");
            if(random.nextBoolean())
                item.setImageUrl("http://1fichier.com/?zou0jfiprx");
            item.setLocation("Кирпичная, 33");
            item.setDateTime("2017-"+(random.nextInt(6)+3)+"-23T18:25:43.511Z");

            return item;

        }
    }
}