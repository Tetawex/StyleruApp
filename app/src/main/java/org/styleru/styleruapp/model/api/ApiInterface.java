package org.styleru.styleruapp.model.api;


import org.styleru.styleruapp.model.dto.EventsRequest;
import org.styleru.styleruapp.model.dto.EventsResponse;
import org.styleru.styleruapp.model.dto.SignUpRequest;
import org.styleru.styleruapp.model.dto.SignUpResponse;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiInterface {
    @POST("requestSignUp")
    Observable<SignUpResponse> requestSignUp(@Body SignUpRequest signUpRequest);

    @POST("requestEvents")
    Observable<EventsResponse> requestEvents(@Body EventsRequest eventsRequest);
}
