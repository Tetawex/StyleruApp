package org.styleru.styleruapp.model.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Пользователь on 03.04.2017.
 */

public class ProfileProjectsStateChangeResponse {
    @SerializedName("new_state")
    @Expose
    private int newState;
    @SerializedName("id")
    @Expose
    private int id;

    public int getNewState() {
        return newState;
    }

    public void setNewState(int newState) {
        this.newState = newState;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

