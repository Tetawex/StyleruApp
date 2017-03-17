package org.styleru.styleruapp.model;

import android.util.Log;

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
<<<<<<< HEAD
        EventsResponse eventsResponse=new EventsResponse();
        List<EventsItem> list=new ArrayList<EventsItem>();
        for(int i=0;i<request.getBatchSize();i++)
            list.add(TestEventsItemFactory.generateRandomItem(request.getOffset()+i));
=======
        if(request.getOffset()==0)
            testEventsItemFactory.reset();
        EventsResponse eventsResponse=new EventsResponse();
        List<EventsItem> list=new ArrayList<EventsItem>();
        for(int i=request.getOffset();i<request.getBatchSize()+request.getOffset();i++)
            list.add(testEventsItemFactory.generateRandomItem(i));
>>>>>>> parent of 2a60caa... Revert "Очень много изменений..."
        eventsResponse.setData(list);
        Log.d("test",eventsResponse.getData().size()+"");
        return Observable.just(eventsResponse);
    }
<<<<<<< HEAD
    private static class TestEventsItemFactory{
        private static EventsItem generateRandomItem(int id){
            Random random=new Random();

=======
    private class TestEventsItemFactory{
        private Random random=new Random(12345);
        public void reset(){
            random=new Random(12345);
        }
        private EventsItem generateRandomItem(int id){
>>>>>>> parent of 2a60caa... Revert "Очень много изменений..."
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