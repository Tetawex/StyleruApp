package org.styleru.styleruapp.view;

import org.styleru.styleruapp.model.dto.EventsItem;

import java.util.List;

/**
 * Created by tetawex on 06.03.17.
 */

public interface EventsView extends View{
    void appendData(List<EventsItem> data);
    void setData(List<EventsItem> data);
    void changeEventState(int id);
    void onDataUpdated();
}
