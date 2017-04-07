package org.styleru.styleruapp.view;

import org.styleru.styleruapp.model.dto.TimelineItem;

import java.util.List;

/**
 * Created by Пользователь on 06.04.2017.
 */

public interface TimelineView extends View{
    void appendData(List<TimelineItem> data);
    void setData(List<TimelineItem> data);
    void onDataUpdated();
}
