package org.styleru.styleruapp.model;

import org.styleru.styleruapp.model.api.ApiService;
import org.styleru.styleruapp.model.cache.Singletons;
import org.styleru.styleruapp.model.dto.ProjectRequest;
import org.styleru.styleruapp.model.dto.ProjectResponse;
import org.styleru.styleruapp.model.dto.RequestVacancyRequest;

import io.reactivex.Completable;
import io.reactivex.Observable;

/**
 * Created by tetawex on 30.04.17.
 */

public class ProjectModelImpl implements ProjectModel {
    private ApiService apiService;
    private String token;

    public ProjectModelImpl() {
        apiService= Singletons.getApiService();
        token=Singletons.getPreferencesManager().getAuthToken();
    }

    @Override
    public Observable<ProjectResponse> getProjectData(int id) {
        return apiService.getApiInterface().getSingleProject(new ProjectRequest(token,id));
    }

    @Override
    public Completable requestVacancy(int id) {
        return apiService.getApiInterface().requestVacancy(new RequestVacancyRequest(id,token));
    }
}
