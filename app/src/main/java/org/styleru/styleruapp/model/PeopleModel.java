package org.styleru.styleruapp.model;

import org.styleru.styleruapp.model.dto.PeopleRequest;
import org.styleru.styleruapp.model.dto.PeopleResponse;
import org.styleru.styleruapp.model.dto.support.PeopleFilter;

import io.reactivex.Observable;

/**
 * Created by Tetawex on 01.04.2017.
 */

public interface PeopleModel {
    Observable<PeopleResponse> getData(int batchSize,int currentId, String requestString, PeopleFilter filter);
}
