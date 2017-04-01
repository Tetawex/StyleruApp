package org.styleru.styleruapp.model.api;

import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiService {

    private static String baseUrl = "http://tatoo.styleru.net/api/";
    private ApiInterface apiInterface;

    public ApiService()
    {
        this(baseUrl);
    }

    public ApiService(String baseUrl)
    {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        apiInterface = retrofit.create(ApiInterface.class);
    }

    public ApiInterface getApiInterface()
    {
        return apiInterface;
    }

}
