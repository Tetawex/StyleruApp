package org.styleru.styleruapp.model.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.styleru.styleruapp.model.dto.support.Department;
import org.styleru.styleruapp.model.dto.support.Subdepartment;

import java.util.List;

/**
 * Created by tetawex on 29.03.17.
 */

public class PeopleItem {
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("last_name")
    @Expose
    private String lastName;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("img")
    @Expose
    private String imageUrl;
    @SerializedName("university_id")
    @Expose
    private int universityId;
    @SerializedName("experience")
    @Expose
    private int experience;
    @SerializedName("subdepartment_ids")
    @Expose
    private List<Integer> subdepartmentIds = null;
    @SerializedName("department_ids")
    @Expose
    private List<Integer> departmentIds = null;
    @SerializedName("subdepartment_names")
    @Expose
    private List<String> subdepartmentNames = null;
    @SerializedName("department_names")
    @Expose
    private List<String> departmentNames;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public int getUniversityId() {
        return universityId;
    }

    public void setUniversityId(int universityId) {
        this.universityId = universityId;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public List<Integer> getSubdepartmentIds() {
        return subdepartmentIds;
    }

    public void setSubdepartmentIds(List<Integer> subdepartmentIds) {
        this.subdepartmentIds = subdepartmentIds;
    }

    public List<Integer> getDepartmentIds() {
        return departmentIds;
    }

    public void setDepartmentIds(List<Integer> departmentIds) {
        this.departmentIds = departmentIds;
    }

    public List<String> getSubdepartmentNames() {
        return subdepartmentNames;
    }

    public void setSubdepartmentNames(List<String> subdepartmentNames) {
        this.subdepartmentNames = subdepartmentNames;
    }

    public List<String> getDepartmentNames() {
        return departmentNames;
    }

    public void setDepartmentNames(List<String> departmentNames) {
        this.departmentNames = departmentNames;
    }

}
