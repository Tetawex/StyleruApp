package org.styleru.styleruapp.model.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Tetawex on 31.03.2017.
 */

public class DepartmentsRequest {
    public DepartmentsRequest(String token, int batchSize, int offset) {
        this.token = token;
        this.batchSize = batchSize;
        this.offset = offset;
    }

    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("batch_size")
    @Expose
    private int batchSize;
    @SerializedName("offset")
    @Expose
    private int offset;

}
