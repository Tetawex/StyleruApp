package org.styleru.styleruapp.model;

import org.styleru.styleruapp.model.api.ApiService;
import org.styleru.styleruapp.model.api.VacancyRequest;
import org.styleru.styleruapp.model.cache.Singletons;
import org.styleru.styleruapp.model.dto.ApproveVacancyRequest;
import org.styleru.styleruapp.model.dto.RecommendVacancyRequest;
import org.styleru.styleruapp.model.dto.VacancyResponse;

import io.reactivex.Completable;
import io.reactivex.Observable;

/**
 * Created by tetawex on 30.04.17.
 */

public class VacancyModelImpl implements VacancyModel {
    private ApiService apiService;
    private String token;

    public VacancyModelImpl() {
        apiService= Singletons.getApiService();
        token=Singletons.getPreferencesManager().getAuthToken();
    }
    @Override
    public Observable<VacancyResponse> getVacancyData(int id) {
        return apiService.getApiInterface()
                .getVacancyData(new VacancyRequest(id,token));
    }

    @Override
    public Completable approveVacancy(int id) {
        return apiService.getApiInterface()
                .approveVacancy(new ApproveVacancyRequest(id,token));
    }

    @Override
    public Completable recommendVacancy(int id) {
        return apiService.getApiInterface()
                .recommendVacancy(new RecommendVacancyRequest(id,token));
    }
}
