package org.styleru.styleruapp.model.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.styleru.styleruapp.model.dto.support.IdItem;

/**
 * Created by tetawex on 06.03.17.
 * It is just a POJO
 */


public class EventsItem implements IdItem {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("status")
    @Expose
    private int state;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("subtitle")
    @Expose
    private String subtitle;
    @SerializedName("image_url")
    @Expose
    private String imageUrl;
    @SerializedName("date_time")
    @Expose
    private String dateTime;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("author")
    @Expose
    private String author;
    @SerializedName("attendants_count")
    @Expose
    private int attendantsCount;
    @SerializedName("view_attendants")
    @Expose
    private boolean viewAttendants;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public boolean isViewAttendants() {
        return viewAttendants;
    }

    public void setViewAttendants(boolean viewAttendants) {
        this.viewAttendants = viewAttendants;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getAttendantsCount() {
        return attendantsCount;
    }

    public void setAttendantsCount(int attendantsCount) {
        this.attendantsCount = attendantsCount;
    }
}
