package org.styleru.styleruapp.model.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.styleru.styleruapp.model.dto.support.Department;
import org.styleru.styleruapp.model.dto.support.Experience;
import org.styleru.styleruapp.model.dto.support.Skill;
import org.styleru.styleruapp.model.dto.support.Subdepartment;
import org.styleru.styleruapp.model.dto.support.University;

import java.util.List;

/**
 * Created by Пользователь on 30.04.2017.
 */

public class ProfileItem {

    @SerializedName("timeline")
    @Expose
    private List<TimelineItem> timeline = null;
    @SerializedName("skills")
    @Expose
    private List<Skill> skills = null;
    @SerializedName("projects")
    @Expose
    private List<ProfileProjectsItem> projects = null;
    @SerializedName("experience")
    @Expose
    private List<Experience> experiences = null;
    @SerializedName("start_date")
    @Expose
    private String startDate;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("last_name")
    @Expose
    private String lastName;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("can_edit")
    @Expose
    private boolean canEdit;
    @SerializedName("summary")
    @Expose
    private String summary;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("image_url")
    @Expose
    private String image_url;

    public List<TimelineItem> getTimeline() {
        return timeline;
    }

    public void setTimeline(List<TimelineItem> timeline) {
        this.timeline = timeline;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    public List<ProfileProjectsItem> getProjects() {
        return projects;
    }

    public void setProjects(List<ProfileProjectsItem> projects) {
        this.projects = projects;
    }

    public List<Experience> getExperiences() {
        return experiences;
    }

    public void setExperiences(List<Experience> experiences) {
        this.experiences = experiences;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isCanEdit() {
        return canEdit;
    }

    public void setCanEdit(boolean canEdit) {
        this.canEdit = canEdit;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}


