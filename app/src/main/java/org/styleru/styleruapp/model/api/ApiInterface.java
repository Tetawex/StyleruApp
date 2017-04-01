package org.styleru.styleruapp.model.api;


import org.styleru.styleruapp.model.dto.EventsRequest;
import org.styleru.styleruapp.model.dto.EventsResponse;
import org.styleru.styleruapp.model.dto.LoginRequest;
import org.styleru.styleruapp.model.dto.LoginResponse;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiInterface {
    @POST("requestSignUp")
    Observable<LoginResponse> requestSignUp(@Body LoginRequest loginRequest);

    @POST("requestEvents")
    Observable<EventsResponse> requestEvents(@Body EventsRequest eventsRequest);
}
