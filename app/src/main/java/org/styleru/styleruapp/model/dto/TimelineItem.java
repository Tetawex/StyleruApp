package org.styleru.styleruapp.model.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.styleru.styleruapp.model.dto.support.IdItem;

/**
 * Created by Пользователь on 06.04.2017.
 */

public class TimelineItem {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("type")
    @Expose
    private int type;
    @SerializedName("is_project")
    @Expose
    private boolean is_projects;
    @SerializedName("is_event")
    @Expose
    private boolean is_event;
    @SerializedName("department_change")
    @Expose
    private boolean department_change;
    @SerializedName("action")
    @Expose
    private String action;
    @SerializedName("action_name")
    @Expose
    private String action_name;
    @SerializedName("action_id")
    @Expose
    private int action_id;
    @SerializedName("date")
    @Expose
    private String date;

    public boolean is_event() {
        return is_event;
    }

    public boolean is_projects() {
        return is_projects;
    }

    public boolean isDepartment_change() {
        return department_change;
    }

    public int getAction_id() {
        return action_id;
    }

    public int getType() {
        return type;
    }

    public String getAction() {
        return action;
    }

    public String getAction_name() {
        return action_name;
    }

    public String getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void setAction_id(int action_id) {
        this.action_id = action_id;
    }

    public void setAction_name(String action_name) {
        this.action_name = action_name;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDepartment_change(boolean department_change) {
        this.department_change = department_change;
    }

    public void setIs_event(boolean is_event) {
        this.is_event = is_event;
    }

    public void setIs_projects(boolean is_projects) {
        this.is_projects = is_projects;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(int type) {
        this.type = type;
    }

}

