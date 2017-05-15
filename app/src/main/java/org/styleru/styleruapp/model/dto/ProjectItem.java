
package org.styleru.styleruapp.model.dto;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.styleru.styleruapp.model.dto.support.Project;
import org.styleru.styleruapp.model.dto.support.Skill;
import org.styleru.styleruapp.model.dto.support.Timeline;

public class ProjectItem {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("last_name")
    @Expose
    private String lastName;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("image_url")
    @Expose
    private String imageUrl;
    @SerializedName("summary")
    @Expose
    private String summary;
    @SerializedName("skills")
    @Expose
    private List<Skill> skills = null;
    @SerializedName("projects")
    @Expose
    private List<Project> projects = null;
    @SerializedName("start_date")
    @Expose
    private Object startDate;
    @SerializedName("timeline")
    @Expose
    private List<Timeline> timeline = null;
    @SerializedName("can_edit")
    @Expose
    private Boolean canEdit;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public Object getStartDate() {
        return startDate;
    }

    public void setStartDate(Object startDate) {
        this.startDate = startDate;
    }

    public List<Timeline> getTimeline() {
        return timeline;
    }

    public void setTimeline(List<Timeline> timeline) {
        this.timeline = timeline;
    }

    public Boolean getCanEdit() {
        return canEdit;
    }

    public void setCanEdit(Boolean canEdit) {
        this.canEdit = canEdit;
    }

}
