package org.styleru.styleruapp.model.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by tetawex on 29.03.17.
 */

public class ProjectsRequest {

    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("batch_size")
    @Expose
    private int batchSize;
    @SerializedName("offset")
    @Expose
    private int offset;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getBatchSize() {
        return batchSize;
    }

    public void setBatchSize(int batchSize) {
        this.batchSize = batchSize;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public ProjectsRequest(String token, int batchSize, int offset) {
        this.token = token;
        this.batchSize = batchSize;
        this.offset = offset;
    }
}
