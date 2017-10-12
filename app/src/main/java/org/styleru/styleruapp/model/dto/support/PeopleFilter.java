package org.styleru.styleruapp.model.dto.support;

import android.util.Log;

import org.styleru.styleruapp.model.dto.PeopleItem;

import java.util.Collections;
import java.util.List;

/**
 * Created by Tetawex on 01.04.2017.
 */

public class PeopleFilter {
    private List<Integer> universityIds;
    private List<Integer> departmentIds;
    private List<Integer> subdepartmentIds;
    private List<Integer> experiences;

    public PeopleFilter(List<Integer> departmentIds,
                        List<Integer> subdepartmentIds,
                        List<Integer> universityIds,
                        List<Integer> experiences) {
        this.universityIds = universityIds;
        this.departmentIds = departmentIds;
        this.subdepartmentIds = subdepartmentIds;
        this.experiences = experiences;
    }

    public List<Integer> getUniversityIds() {
        return universityIds;
    }

    public void setUniversityIds(List<Integer> universityIds) {
        this.universityIds = universityIds;
    }

    public List<Integer> getDepartmentIds() {
        return departmentIds;
    }

    public void setDepartmentIds(List<Integer> departmentIds) {
        this.departmentIds = departmentIds;
    }

    public List<Integer> getSubdepartmentIds() {
        return subdepartmentIds;
    }

    public void setSubdepartmentIds(List<Integer> subdepartmentIds) {
        this.subdepartmentIds = subdepartmentIds;
    }

    public List<Integer> getExperiences() {
        return experiences;
    }

    public void setExperiences(List<Integer> experiences) {
        this.experiences = experiences;
    }

    public PeopleFilter() {
        this(Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), Collections.emptyList());
    }

    public boolean valid(PeopleItem item) {
        if (getDepartmentIds().size() != 0 &&
                Collections.disjoint(getDepartmentIds(), item.getDepartmentIds()))
            return false;
        if (getSubdepartmentIds().size() != 0 &&
                Collections.disjoint(getSubdepartmentIds(), item.getSubdepartmentIds()))
            return false;
        if (getUniversityIds().size() != 0) {
            boolean flag = false;
            for (Integer integer : getUniversityIds()) {
                if (integer.equals(item.getUniversityId()))
                    flag = true;
            }
            return flag;
        }
        if (getExperiences().size() != 0) {
            boolean flag = false;
            for (Integer integer : getExperiences()) {
                if (integer.equals(item.getExperience()))
                    flag = true;
            }
            return flag;
        }
        return true;
    }
}
