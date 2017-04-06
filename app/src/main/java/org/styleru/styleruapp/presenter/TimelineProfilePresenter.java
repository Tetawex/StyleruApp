package org.styleru.styleruapp.presenter;

/**
 * Created by Пользователь on 06.04.2017.
 */

    public interface TimelineProfilePresenter extends Presenter{
        void onProfileTimelineAppend(int offset);
        void onProfileTimelineUpdate();
        void onProfileTimelineStatusChange(int id, boolean desiredStatus);
}
