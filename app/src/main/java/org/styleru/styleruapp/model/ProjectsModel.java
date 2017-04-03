package org.styleru.styleruapp.model;

import org.styleru.styleruapp.model.dto.ProjectsItem;
import org.styleru.styleruapp.model.dto.ProjectsRequest;
import org.styleru.styleruapp.model.dto.ProjectsResponse;
import org.styleru.styleruapp.model.dto.support.PeopleFilter;
import org.styleru.styleruapp.model.dto.support.ProjectsFilter;
import org.styleru.styleruapp.util.ErrorListener;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Action;

/**
 * Created by tetawex on 29.03.17.
 */

public interface ProjectsModel extends Model{
    Observable<List<ProjectsItem>> getData(int batchSize, int currentId);
    void setFilter(ProjectsFilter filter);
    void setRequestString(String requestString);
    void updateCachedData();
    void setDataChangedListener(Action dataChangedListener);
    void setDataResetListener(Action dataChangedListener);
    void setErrorListener(ErrorListener errorListener);
}
