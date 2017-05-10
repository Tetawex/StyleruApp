
package org.styleru.styleruapp.model.dto.support;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Request implements IdItem{

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("recommended")
    @Expose
    private boolean recommended;
    @SerializedName("id_people")
    @Expose
    private int peopleId;
    @SerializedName("img_people")
    @Expose
    private String image;
    @SerializedName("name_people")
    @Expose
    private String peopleName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPeopleId() {
        return peopleId;
    }

    public void setPeopleId(int peopleId) {
        this.peopleId = peopleId;
    }

    public String getPeopleName() {
        return peopleName;
    }

    public void setPeopleName(String peopleName) {
        this.peopleName = peopleName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isRecommended() {
        return recommended;
    }

    public void setRecommended(boolean recommended) {
        this.recommended = recommended;
    }
}
