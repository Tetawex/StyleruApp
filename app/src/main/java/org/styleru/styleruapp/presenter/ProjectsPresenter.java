package org.styleru.styleruapp.presenter;

import org.styleru.styleruapp.model.dto.support.PeopleFilter;
import org.styleru.styleruapp.model.dto.support.ProjectsFilter;

/**
 * Created by tetawex on 29.03.17.
 */

public interface ProjectsPresenter extends Presenter{
    void onDataAppend(int offset, int batchSize);
    void onDataUpdate(int batchSize);
    void onSetFilter(ProjectsFilter filter);
    void onModelUpdateCachedData();
    void onSetRequestString(String string);
}
