package org.styleru.styleruapp.model.dto.support;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TimelineEvent {

    @SerializedName("join")
    @Expose
    private boolean join;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("project_name")
    @Expose
    private String projectName;
    @SerializedName("project_id")
    @Expose
    private int projectId;

    public boolean isJoin() {
        return join;
    }

    public void setJoin(boolean join) {
        this.join = join;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

}