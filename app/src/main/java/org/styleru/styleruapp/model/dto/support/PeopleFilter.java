package org.styleru.styleruapp.model.dto.support;

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

    public PeopleFilter(List<Integer> universityIds, List<Integer> departmentIds,
                        List<Integer> subdepartmentIds, List<Integer> experiences) {
        this.universityIds = universityIds;
        this.departmentIds = departmentIds;
        this.subdepartmentIds = subdepartmentIds;
        this.experiences = experiences;
    }
    public PeopleFilter() {
        this(Collections.emptyList(),Collections.emptyList(),Collections.emptyList(),Collections.emptyList());
    }
}
