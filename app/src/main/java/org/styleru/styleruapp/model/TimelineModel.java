package org.styleru.styleruapp.model;

import org.styleru.styleruapp.model.dto.TimelineRequest;
import org.styleru.styleruapp.model.dto.TimelineResponse;
import org.styleru.styleruapp.model.dto.TimelineStateChangeRequest;
import org.styleru.styleruapp.model.dto.TimelineStateChangeResponse;

import io.reactivex.Observable;

/**
 * Created by Пользователь on 06.04.2017.
 */

public interface TimelineModel extends Model{
        Observable<TimelineResponse> getData(TimelineRequest request);
        Observable<TimelineStateChangeResponse> getChangedState(TimelineStateChangeRequest request);
    }