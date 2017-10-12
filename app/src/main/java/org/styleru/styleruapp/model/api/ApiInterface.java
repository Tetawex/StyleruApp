package org.styleru.styleruapp.model.api;


import org.styleru.styleruapp.model.dto.ApproveVacancyRequest;
import org.styleru.styleruapp.model.dto.DepartmentsRequest;
import org.styleru.styleruapp.model.dto.DepartmentsResponse;
import org.styleru.styleruapp.model.dto.EventStateChangeRequest;
import org.styleru.styleruapp.model.dto.EventStateChangeResponse;
import org.styleru.styleruapp.model.dto.EventsRequest;
import org.styleru.styleruapp.model.dto.EventsResponse;
import org.styleru.styleruapp.model.dto.FilterModelRequest;
import org.styleru.styleruapp.model.dto.FilterModelResponse;
import org.styleru.styleruapp.model.dto.LoginRequest;
import org.styleru.styleruapp.model.dto.LoginResponse;
import org.styleru.styleruapp.model.dto.LogoutRequest;
import org.styleru.styleruapp.model.dto.PeopleRequest;
import org.styleru.styleruapp.model.dto.PeopleResponse;

import org.styleru.styleruapp.model.dto.ProfileRequest;
import org.styleru.styleruapp.model.dto.ProfileResponse;
import org.styleru.styleruapp.model.dto.ProjectRequest;
import org.styleru.styleruapp.model.dto.ProjectResponse;
import org.styleru.styleruapp.model.dto.ProjectsRequest;
import org.styleru.styleruapp.model.dto.ProjectsResponse;
import org.styleru.styleruapp.model.dto.RecommendVacancyRequest;
import org.styleru.styleruapp.model.dto.RequestVacancyRequest;
import org.styleru.styleruapp.model.dto.SettingsDownloadRequest;
import org.styleru.styleruapp.model.dto.SettingsDownloadResponse;
import org.styleru.styleruapp.model.dto.SettingsUploadRequest;
import org.styleru.styleruapp.model.dto.SettingsUploadResponse;
import org.styleru.styleruapp.model.dto.SingleProfileRequest;
import org.styleru.styleruapp.model.dto.SingleProfileResponse;
import org.styleru.styleruapp.model.dto.TimelineRequest;
import org.styleru.styleruapp.model.dto.TimelineResponse;
import org.styleru.styleruapp.model.dto.VacancyRequest;
import org.styleru.styleruapp.model.dto.VacancyResponse;
import org.styleru.styleruapp.model.dto.ValidateTokenRequest;

import io.reactivex.Completable;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiInterface {
    @POST("login")
    Observable<LoginResponse> login(@Body LoginRequest request);

    @POST("checkValidate")
    Observable<LoginResponse> validateToken(@Body ValidateTokenRequest request);

    @POST("disconnect")
    Completable logout(@Body LogoutRequest request);

    @POST("filterData")
    Observable<FilterModelResponse> getFilterModel(@Body FilterModelRequest request);

    @POST("settingsData")
    Observable<SettingsDownloadResponse> getSettingsModel(@Body SettingsDownloadRequest request);

    @POST("settingsChange")
    Observable<SettingsUploadResponse> uploadSettings(@Body SettingsUploadRequest request);

    @POST("eventRegistration")
    Observable<EventStateChangeResponse> changeEventState(@Body EventStateChangeRequest request);

    @POST("allDepartment")
    Observable<DepartmentsResponse> getDepartments(@Body DepartmentsRequest request);

    @POST("allEvents")
    Observable<EventsResponse> getEvents(@Body EventsRequest request);

    @POST("allPeople")
    Observable<PeopleResponse> getPeople(@Body PeopleRequest request);

    @POST("allProject")
    Observable<ProjectsResponse> getProjects(@Body ProjectsRequest request);

    @POST("singleTimeline")
    Observable<TimelineResponse> getTimeline(@Body TimelineRequest request);

    //@POST("singlePersonProjects")
    //Observable<ProfileProjectsResponse> getProfileProjects(@Body ProfileProjectsRequest request);
    @POST("singlePerson")
    Observable<SingleProfileResponse> getSingleProfile(@Body SingleProfileRequest request);

    @POST("singleProject")
    Observable<ProjectResponse> getSingleProject(@Body ProjectRequest request);

    @POST("vacancy")
    Observable<VacancyResponse> getVacancyData(@Body VacancyRequest request);

    @POST("recommendVacancy")
    Completable recommendVacancy(@Body RecommendVacancyRequest request);

    @POST("approveVacancy")
    Completable approveVacancy(@Body ApproveVacancyRequest request);

    @POST("requestVacancy")
    Completable requestVacancy(@Body RequestVacancyRequest request);

    @POST("profile")
    Observable<ProfileResponse> getSingleProfileFull(@Body ProfileRequest request);
}
