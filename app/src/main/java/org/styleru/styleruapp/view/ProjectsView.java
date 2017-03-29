package org.styleru.styleruapp.view;

import org.styleru.styleruapp.model.dto.EventsItem;
import org.styleru.styleruapp.model.dto.ProjectsItem;

import java.util.List;

/**
 * Created by tetawex on 29.03.17.
 */

public interface ProjectsView extends View{
    void appendData(List<ProjectsItem> data);
    void setData(List<ProjectsItem> data);
    void onDataUpdated();
}
