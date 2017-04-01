package org.styleru.styleruapp.presenter;

import org.styleru.styleruapp.model.dto.support.PeopleFilter;

/**
 * Created by Tetawex on 01.04.2017.
 */

public interface PeoplePresenter extends Presenter {
    void onPeopleAppend(int offset, int batchSize,String requestString,PeopleFilter filter);
    void onPeopleUpdate(int batchSize,String requestString,PeopleFilter filter);
    void onSetFilterMode(PeopleFilter filter);
}
