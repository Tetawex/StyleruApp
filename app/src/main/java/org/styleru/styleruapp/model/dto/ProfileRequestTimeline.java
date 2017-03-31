package org.styleru.styleruapp.model.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Tetawex on 31.03.2017.
 */

public class ProfileRequestTimeline {
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("user_id")
    @Expose
    private int userId;
    @SerializedName("batch_size")
    @Expose
    private int batchSize;
    @SerializedName("offset")
    @Expose
    private int offset;

    public ProfileRequestTimeline(String token, int userId, int batchSize, int offset) {
        this.token = token;
        this.userId = userId;
        this.batchSize = batchSize;
        this.offset = offset;
    }
}
