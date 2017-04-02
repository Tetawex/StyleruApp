package org.styleru.styleruapp.model;

import org.styleru.styleruapp.model.dto.PeopleItem;
import org.styleru.styleruapp.model.dto.PeopleRequest;
import org.styleru.styleruapp.model.dto.PeopleResponse;
import org.styleru.styleruapp.model.dto.support.PeopleFilter;
import org.styleru.styleruapp.util.ErrorListener;

import java.util.List;
import java.util.function.Consumer;

import io.reactivex.Observable;
import io.reactivex.functions.Action;

/**
 * Created by Tetawex on 01.04.2017.
 */

public interface PeopleModel {
    Observable<List<PeopleItem>> getData(int batchSize, int currentId);
    void setFilter(PeopleFilter filter);
    void setRequestString(String requestString);
    void updateCachedData();
    void setDataChangedListener(Action dataChangedListener);
    void setDataResetListener(Action dataChangedListener);
    void setErrorListener(ErrorListener errorListener);
}
