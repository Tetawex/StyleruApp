package org.styleru.styleruapp.model.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.styleru.styleruapp.model.dto.support.Skill;

import java.util.List;

/**
 * Created by Пользователь on 05.04.2017.
 */

public class SingleProfileResponse {
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("first_name")
    @Expose
    private String first_name;
    @SerializedName("last_name")
    @Expose
    private String last_name;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("image_url")
    @Expose
    private String image_url;
    @SerializedName("summary")
    @Expose
    private String summary;
    @SerializedName("skills")
    @Expose
    private List<Skill> skills = null;

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public String getEmail() {
        return email;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getPhone() {
        return phone;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSummary() {
        return summary;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

}


