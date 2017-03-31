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
    private Integer batchSize;
    @SerializedName("offset")
    @Expose
    private Integer offset;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getBatchSize() {
        return batchSize;
    }

    public void setBatchSize(Integer batchSize) {
        this.batchSize = batchSize;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

}
