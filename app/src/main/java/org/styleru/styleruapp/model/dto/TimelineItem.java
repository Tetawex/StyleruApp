package org.styleru.styleruapp.model.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.styleru.styleruapp.model.dto.support.IdItem;

/**
 * Created by Пользователь on 06.04.2017.
 */

public class TimelineItem implements IdItem {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("surname")
    @Expose
    private String surname;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("status")
    @Expose
    private boolean status;
    @SerializedName("project")
    @Expose
    private String project;


    public boolean getStatus() {
        return status;
    }

    public String getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getProject() {
        return project;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setProject(String project) {
        this.project = project;
    }
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
    }
}

