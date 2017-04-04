package org.styleru.styleruapp.model.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Пользователь on 04.04.2017.
 */

public class ProjectResponse {
    @Expose
    @SerializedName("manager")
    private String manager;
    @Expose
    @SerializedName("start")
    private String start;
    @Expose
    @SerializedName("deadline")
    private String deadline;
    @Expose
    @SerializedName("progress")
    private int progress;
    @Expose
    @SerializedName("data1")
    private List<MembersItem> data1;
    @Expose
    @SerializedName("data2")
    private List<DutiesItem> data2;

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public List<MembersItem> getData1() {
        return data1;
    }

    public void setData1(List<MembersItem> data1) {
        this.data1 = data1;
    }

    public List<DutiesItem> getData2() {return data2;}
    public void setData2(List<DutiesItem> data2){this.data2=data2;}
}