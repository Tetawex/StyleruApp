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
    private TestEventsItemFactory testEventsItemFactory;

    public TestEventsModelImpl() {
        testEventsItemFactory = new TestEventsItemFactory();
    }

    @Override
    public Observable<EventsResponse> getData(EventsRequest request) {
        if(request.getOffset()<=5)
            testEventsItemFactory.reset();
        EventsResponse eventsResponse=new EventsResponse();
        List<EventsItem> list=new ArrayList<EventsItem>();
        for(int i=0;i<request.getBatchSize();i++)
            list.add(testEventsItemFactory.generateRandomItem(request.getOffset()+i));
        eventsResponse.setData(list);
        return Observable.just(eventsResponse);
    }
    private class TestEventsItemFactory{
        private Random random=new Random(1234);
        public void reset(){
            random=new Random(1234);
        }
        private EventsItem generateRandomItem(int id){
            EventsItem item=new EventsItem();
            item.setTitle("Общая встреча организации");
            item.setSubtitle("By Аня Подображных");
            item.setId(id);

            int r=random.nextInt(4);
            if(r==1)
                item.setImageUrl("http://1fichier.com/?zou0jfiprx");
            else if(r==2)
                item.setImageUrl("https://1fichier.com/?knglu55wha");
            else if(r==3)
                item.setImageUrl("https://1fichier.com/?df9sro0hk4");
            else
                item.setImageUrl("");
            item.setLocation("Кирпичная, 33");
            item.setDateTime("2017-"+(random.nextInt(6)+3)+"-23T"+(random.nextInt(9)+10)+":00:43.511Z");

            return item;

        }
    }
}