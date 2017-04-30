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
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("first_name")
    @Expose
    private String first_name;
    @SerializedName("last_name")
    @Expose
    private String last_name;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("summary")
    @Expose
    private String  summary;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("image_url")
    @Expose
    private String image_url;

    public List<Experience> getExperiences() {
        return experiences;
    }

    public List<ProfileProjectsItem> getProjects() {
        return projects;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public List<TimelineItem> getTimeline() {
        return timeline;
    }

    public String getEmail() {
        return email;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getId() {
        return id;
    }

    public String getImage_url() {
        return image_url;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getPhone() {
        return phone;
    }

    public String getSummary() {
        return summary;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setExperiences(List<Experience> experiences) {
        this.experiences = experiences;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setProjects(List<ProfileProjectsItem> projects) {
        this.projects = projects;
    }
    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public void setTimeline(List<TimelineItem> timeline) {
        this.timeline = timeline;
    }
}

