package org.styleru.styleruapp.presenter;

import org.styleru.styleruapp.model.dto.support.PeopleFilter;

/**
 * Created by Tetawex on 01.04.2017.
 */

public interface PeoplePresenter extends Presenter {
    void onDataAppend(int offset, int batchSize);
    void onDataUpdate(int batchSize);
    void onSetFilter(PeopleFilter filter);
    void onSetRequestString(String string);
}
