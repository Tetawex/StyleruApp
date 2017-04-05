package org.styleru.styleruapp.model.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Пользователь on 04.04.2017.
 */

public class DutiesItem {
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("role")
    @Expose
    private String role;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
