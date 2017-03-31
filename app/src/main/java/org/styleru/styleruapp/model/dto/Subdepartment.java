package org.styleru.styleruapp.model.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Tetawex on 31.03.2017.
 */

public class Subdepartment {
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("manager")
    @Expose
    private String manager;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("img_url")
    @Expose
    private String imgUrl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

}
