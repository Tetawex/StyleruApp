
package org.styleru.styleruapp.model.dto;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.styleru.styleruapp.model.dto.support.Department;
import org.styleru.styleruapp.model.dto.support.Experience;
import org.styleru.styleruapp.model.dto.support.Subdepartment;
import org.styleru.styleruapp.model.dto.support.University;

public class FilterModelResponse {

    @SerializedName("departments")
    @Expose
    private List<Department> departments = null;
    @SerializedName("subdepartments")
    @Expose
    private List<Subdepartment> subdepartments = null;
    @SerializedName("universities")
    @Expose
    private List<University> universities = null;
    @SerializedName("experience")
    @Expose
    private List<Experience> experiences = null;

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }

    public List<Subdepartment> getSubdepartments() {
        return subdepartments;
    }

    public void setSubdepartments(List<Subdepartment> subdepartments) {
        this.subdepartments = subdepartments;
    }

    public List<University> getUniversities() {
        return universities;
    }

    public void setUniversities(List<University> universities) {
        this.universities = universities;
    }

    public List<Experience> getExperiences() {
        return experiences;
    }

    public void setExperiences(List<Experience> experience) {
        this.experiences = experiences;
    }

}
