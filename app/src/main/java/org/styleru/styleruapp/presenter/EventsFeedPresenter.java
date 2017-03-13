package org.styleru.styleruapp.presenter;

/**
 * Created by tetawex on 06.03.17.
 */

public interface EventsFeedPresenter extends Presenter{
    void onEventsAppend(int offset,int batchSize);
    void onEventsUpdate(int batchSize);
void onEventStatusChange(int id, boolean desiredStatus);
}