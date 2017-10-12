package org.styleru.styleruapp.model;

import org.styleru.styleruapp.model.dto.EventStateChangeRequest;
import org.styleru.styleruapp.model.dto.EventStateChangeResponse;
import org.styleru.styleruapp.model.dto.EventsRequest;
import org.styleru.styleruapp.model.dto.EventsResponse;

import io.reactivex.Observable;


/**
 * Created by tetawex on 06.03.17.
 */

public interface EventsModel extends Model {
    Observable<EventsResponse> getData(int offset, int batchSize);

    Observable<EventStateChangeResponse> getChangedState(int id);
}
