package org.styleru.styleruapp.presenter;

/**
 * Created by Пользователь on 06.04.2017.
 */

public interface TimelinePresenter extends Presenter{
    void onTimelineAppend(int offset,int batchSize);
    void onTimelineUpdate(int batchSize);
    void onTimelineStatusChange(int id, boolean desiredStatus);
}
