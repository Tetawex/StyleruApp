package org.styleru.styleruapp.model.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Пользователь on 04.04.2017.
 */

public class ProjectResponse {
    @Expose
    @SerializedName("name")
    private String name;
    @Expose
    @SerializedName("view_submissions")
    private boolean viewSubmissions;
    @Expose
    @SerializedName("manager_name")
    private String managerName;
    @SerializedName("description")
    private String description;
    @Expose
    @SerializedName("id_manager")
    private int managerId;
    @Expose
    @SerializedName("date_time_start")
    private String dateTimeStart;
    @Expose
    @SerializedName("date_time_end")
    private String dateTimeEnd;
    @Expose
    @SerializedName("completion")
    private int completion;
    @Expose
    @SerializedName("participants")
    private List<ParticipantsItem> participants;
    @Expose
    @SerializedName("vacancies")
    private List<VacanciesItem> vacancies;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isViewSubmissions() {
        return viewSubmissions;
    }

    public void setViewSubmissions(boolean viewSubmissions) {
        this.viewSubmissions = viewSubmissions;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public int getManagerId() {
        return managerId;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }

    public String getDateTimeStart() {
        return dateTimeStart;
    }

    public void setDateTimeStart(String dateTimeStart) {
        this.dateTimeStart = dateTimeStart;
    }

    public String getDateTimeEnd() {
        return dateTimeEnd;
    }

    public void setDateTimeEnd(String dateTimeEnd) {
        this.dateTimeEnd = dateTimeEnd;
    }

    public int getCompletion() {
        return completion;
    }

    public void setCompletion(int completion) {
        this.completion = completion;
    }

    public List<ParticipantsItem> getParticipants() {
        return participants;
    }

    public void setParticipants(List<ParticipantsItem> participants) {
        this.participants = participants;
    }

    public List<VacanciesItem> getVacancies() {
        return vacancies;
    }

    public void setVacancies(List<VacanciesItem> vacancies) {
        this.vacancies = vacancies;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}