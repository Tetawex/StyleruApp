package org.styleru.styleruapp.model.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by tetawex on 29.03.17.
 */

public class ProjectsItem {
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("manager_name")
    @Expose
    private String managerName;
    @SerializedName("vacant_places")
    @Expose
    private boolean vacantPlaces;
    @SerializedName("finished")
    @Expose
    private boolean finished;
    @SerializedName("end_date_time")
    @Expose
    private String endDateTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public boolean isVacantPlaces() {
        return vacantPlaces;
    }

    public void setVacantPlaces(boolean vacantPlaces) {
        this.vacantPlaces = vacantPlaces;
    }

    public String getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(String endDateTime) {
        this.endDateTime = endDateTime;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }
}
