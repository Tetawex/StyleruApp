package org.styleru.styleruapp.view;

import org.styleru.styleruapp.model.dto.EventsItem;

import java.util.List;

/**
 * Created by tetawex on 06.03.17.
 */

public interface EventsView extends View {
    void appendData(List<EventsItem> data);

    void setData(List<EventsItem> data);

    void changeEventStateSuccess(int id, int state);

    void changeEventStateFail(int id);

    void requestChangeEventState(int id);

    void onDataUpdated();
}
