package org.styleru.styleruapp.model.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by tetawex on 25.04.17.
 */

public class RequestVacancyRequest {
    @SerializedName("id_vacancy")
    @Expose
    private int vacancyId;
    @SerializedName("token")
    @Expose
    private String token;

    public RequestVacancyRequest(int vacancyId, String token) {
        this.vacancyId = vacancyId;
        this.token = token;
    }
}
