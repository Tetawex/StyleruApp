package org.styleru.styleruapp.view;

import org.styleru.styleruapp.model.dto.FilterModelResponse;
import org.styleru.styleruapp.model.dto.PeopleItem;
import org.styleru.styleruapp.model.dto.ProjectsItem;

import java.util.List;

/**
 * Created by tetawex on 29.03.17.
 */

public interface PeopleView extends View{
    void setFilterModel(FilterModelResponse model);
    void appendData(List<PeopleItem> data);
    void setData(List<PeopleItem> data);
    void onDataUpdated();
}
