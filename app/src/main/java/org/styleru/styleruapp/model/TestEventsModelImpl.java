package org.styleru.styleruapp.model;

import android.util.Log;

import org.styleru.styleruapp.model.dto.EventStateChangeRequest;
import org.styleru.styleruapp.model.dto.EventStateChangeResponse;
import org.styleru.styleruapp.model.dto.EventsItem;
import org.styleru.styleruapp.model.dto.EventsRequest;
import org.styleru.styleruapp.model.dto.EventsResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import io.reactivex.Observable;

/**
 * Created by tetawex on 29.03.17.
 */
@Deprecated
public class TestEventsModelImpl {
    private TestEventsItemFactory testEventsItemFactory;

    public TestEventsModelImpl() {
        testEventsItemFactory = new TestEventsItemFactory();
    }

    public Observable<EventsResponse> getData(EventsRequest request) {
        if (request.getOffset() == 0)
            testEventsItemFactory.reset();
        EventsResponse eventsResponse = new EventsResponse();
        List<EventsItem> list = new ArrayList<EventsItem>();
        for (int i = request.getOffset(); i < request.getBatchSize() + request.getOffset(); i++)
            list.add(testEventsItemFactory.generateRandomItem(i));
        eventsResponse.setData(list);
        Log.d("test", eventsResponse.getData().size() + "");
        return Observable.just(eventsResponse);
    }

    public Observable<EventStateChangeResponse> getChangedState(EventStateChangeRequest request) {
        return null;
    }

    private class TestEventsItemFactory {
        private Random random = new Random(12345);

        public void reset() {
            random = new Random(12345);
        }

        private EventsItem generateRandomItem(int id) {
            EventsItem item = new EventsItem();
            item.setTitle("Общая встреча организации");
            item.setSubtitle("Lorem ipsum dolor кiт amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.");
            item.setId(id);
            item.setViewAttendants(random.nextBoolean());
            item.setAuthor("By Аня Подображных");

            int r = random.nextInt(5);
            if (r == 1)
                item.setImageUrl("http://1fichier.com/?zou0jfiprx");
            else if (r == 2)
                item.setImageUrl("https://1fichier.com/?knglu55wha");
            else if (r == 3)
                item.setImageUrl("https://1fichier.com/?df9sro0hk4");
            else if (r == 4)
                item.setImageUrl("https://1fichier.com/?7y42cmkp7b");
            else
                item.setImageUrl("");
            item.setLocation("Кирпичная, " + (random.nextInt(80) + 10));
            item.setDateTime("2017-" + (random.nextInt(6) + 3) + "-23 " + (random.nextInt(9) + 10) + ":00:43");

            return item;

        }
    }
}