package org.styleru.styleruapp.presenter;

/**
 * Created by tetawex on 06.03.17.
 */

public interface EventsPresenter extends Presenter {
    void onDataAppend(int offset, int batchSize);

    void onDataUpdate(int batchSize);

    void onEventStateChange(int id);
}