package org.styleru.styleruapp.model.cache;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.styleru.styleruapp.model.dto.LoginResponse;

/**
 * Created by Tetawex on 31.03.2017.
 */

public class UserInfo {
    @Expose
    @SerializedName("user_id")
    private int userId;
    @Expose
    @SerializedName("img_url")
    private String imageUrl;
    @Expose
    @SerializedName("first_name")
    private String firstName;
    @Expose
    @SerializedName("last_name")
    private String lastName;
    @Expose
    @SerializedName("token")
    private String token;
    @Expose
    @SerializedName("email")
    private String email;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserInfo(LoginResponse response) {
        this.userId = response.getUserId();
        this.imageUrl = response.getImageUrl();
        this.firstName = response.getFirstName();
        this.lastName = response.getLastName();
        this.token = response.getToken();
        this.email = response.getEmail();
    }
}
