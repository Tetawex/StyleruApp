package org.styleru.styleruapp.model.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.styleru.styleruapp.model.dto.support.Settings;

/**
 * Created by Tetawex on 31.03.2017.
 */

public class ProfileRequestMain {
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("user_id")
    @Expose
    private int userId;

    public ProfileRequestMain(String token, int userId) {
        this.token = token;
        this.userId = userId;
    }
}
