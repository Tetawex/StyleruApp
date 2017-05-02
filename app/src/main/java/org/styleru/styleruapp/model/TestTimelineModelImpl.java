package org.styleru.styleruapp.model;

import android.util.Log;

import org.styleru.styleruapp.model.dto.TimelineItem;
import org.styleru.styleruapp.model.dto.TimelineRequest;
import org.styleru.styleruapp.model.dto.TimelineResponse;
import org.styleru.styleruapp.model.dto.TimelineStateChangeRequest;
import org.styleru.styleruapp.model.dto.TimelineStateChangeResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import io.reactivex.Observable;

/**
 * Created by Пользователь on 06.04.2017.
 */

public class TestTimelineModelImpl implements TimelineModel{
    private TestTimelineItemFactory testTimelineItemFactory;

    public TestTimelineModelImpl() {
        testTimelineItemFactory = new TestTimelineItemFactory();
    }

    public Observable<TimelineResponse> getData(TimelineRequest request) {
        if(request.getOffset()==0)
            testTimelineItemFactory.reset();
        TimelineResponse timelineResponse=new TimelineResponse();
        List<TimelineItem> list=new ArrayList<TimelineItem>();
        for(int i=request.getOffset();i<request.getBatchSize()+request.getOffset();i++)
            list.add(testTimelineItemFactory.generateRandomItem(i));
        timelineResponse.setData(list);
        Log.d("test",timelineResponse.getData().size()+"");
        return Observable.just(timelineResponse);
    }

    public Observable<TimelineStateChangeResponse> getChangedState(TimelineStateChangeRequest request) {
        return null;
    }

    private class TestTimelineItemFactory{
        private Random random=new Random(12345);
        public void reset(){
            random=new Random(12345);
        }
        private TimelineItem generateRandomItem(int id){
            TimelineItem item=new TimelineItem();
           item.setDate("29.05.12");
            item.setName("Станислав");
            Log.d("Timeline",item.getName());
//            item.setProject("NAMED");
//
//            final Random random = new Random();
//
//            item.setStatus(random.nextBoolean());
//            item.setSurname("Мясников");
            return item;

        }
    }
}