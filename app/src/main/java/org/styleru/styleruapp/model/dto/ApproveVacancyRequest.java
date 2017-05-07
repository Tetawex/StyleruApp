package org.styleru.styleruapp.model.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by tetawex on 25.04.17.
 */

public class ApproveVacancyRequest {
    @SerializedName("id_request")
    @Expose
    private int requestId;
    @SerializedName("token")
    @Expose
    private String token;

    public ApproveVacancyRequest(int requestId, String token) {
        this.requestId = requestId;
        this.token = token;
    }
}
