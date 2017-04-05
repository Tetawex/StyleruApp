package org.styleru.styleruapp.view;

import org.styleru.styleruapp.model.dto.DepartmentsItem;
import org.styleru.styleruapp.model.dto.EventsItem;

import java.util.List;

/**
 * Created by tetawex on 06.03.17.
 */

public interface DepartmentsView extends View{
    void appendData(List<DepartmentsItem> data);
    void setData(List<DepartmentsItem> data);
    void onDataUpdated();
}
