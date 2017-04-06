package org.styleru.styleruapp.model.api;


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
import org.styleru.styleruapp.model.dto.PeopleRequest;
import org.styleru.styleruapp.model.dto.PeopleResponse;
import org.styleru.styleruapp.model.dto.ProjectsRequest;
import org.styleru.styleruapp.model.dto.ProjectsResponse;
import org.styleru.styleruapp.model.dto.ValidateTokenRequest;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {
    @POST("login")
    Observable<LoginResponse> login(@Body LoginRequest request);
    @POST("checkValidate")
    Observable<LoginResponse> validateToken(@Body ValidateTokenRequest request);
    @POST("filterData")
    Observable<FilterModelResponse> getFilterModel(@Body FilterModelRequest request);
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

}
