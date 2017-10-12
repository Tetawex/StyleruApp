package org.styleru.styleruapp.model;

import org.styleru.styleruapp.model.api.ApiService;
import org.styleru.styleruapp.model.cache.Singletons;
import org.styleru.styleruapp.model.dto.DepartmentsRequest;
import org.styleru.styleruapp.model.dto.DepartmentsResponse;
import org.styleru.styleruapp.model.dto.EventStateChangeResponse;
import org.styleru.styleruapp.model.dto.EventsRequest;
import org.styleru.styleruapp.model.dto.EventsResponse;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by tetawex on 29.03.17.
 */

public class DepartmentsModelImpl implements DepartmentsModel {
    private ApiService apiService;
    private String authToken;

    public DepartmentsModelImpl() {
        apiService = Singletons.getApiService();
        authToken = Singletons.getPreferencesManager().getAuthToken();
    }

    @Override
    public Observable<DepartmentsResponse> getData(int offset, int batchSize) {
        return apiService.getApiInterface()
                .getDepartments(new DepartmentsRequest(authToken, batchSize, offset))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}