package org.styleru.styleruapp.model.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Пользователь on 05.04.2017.
 */

public class SingleProfileResponse {
    @Expose
    @SerializedName("name")
    private String name;
    @Expose
    @SerializedName("surname")
    private String surname;
    @Expose
    @SerializedName("phone")
    private String phone;
    @Expose
    @SerializedName("mail")
    private String mail;
    @Expose
    @SerializedName("feed")
    private String feed;
    @Expose
    @SerializedName("data")
    private List<CompetItem> data;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public String getSurname() {
        return surname;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getFeed() {
        return feed;
    }

    public void setFeed(String feed) {
        this.feed = feed;
    }

    public List<CompetItem> getData() {
        return data;
    }

    public void setData(List<CompetItem> data1) {
        this.data = data;
    }

}