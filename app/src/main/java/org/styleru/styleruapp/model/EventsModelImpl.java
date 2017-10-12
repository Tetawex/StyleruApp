package org.styleru.styleruapp.model;

import android.util.Log;

import org.styleru.styleruapp.model.api.ApiService;
import org.styleru.styleruapp.model.cache.Singletons;
import org.styleru.styleruapp.model.dto.EventStateChangeRequest;
import org.styleru.styleruapp.model.dto.EventStateChangeResponse;
import org.styleru.styleruapp.model.dto.EventsItem;
import org.styleru.styleruapp.model.dto.EventsRequest;
import org.styleru.styleruapp.model.dto.EventsResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by tetawex on 29.03.17.
 */

public class EventsModelImpl implements EventsModel {
    private ApiService apiService;
    private String authToken;

    public EventsModelImpl() {
        apiService = Singletons.getApiService();
        authToken = Singletons.getPreferencesManager().getAuthToken();
    }

    @Override
    public Observable<EventsResponse> getData(int offset, int batchSize) {
        return apiService.getApiInterface()
                .getEvents(new EventsRequest(authToken, batchSize, offset))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<EventStateChangeResponse> getChangedState(int id) {
        return apiService.getApiInterface()
                .changeEventState(new EventStateChangeRequest(authToken, id))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}