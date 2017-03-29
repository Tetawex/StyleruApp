package org.styleru.styleruapp.model;

import org.styleru.styleruapp.model.dto.ProjectsRequest;
import org.styleru.styleruapp.model.dto.ProjectsResponse;

import io.reactivex.Observable;

/**
 * Created by tetawex on 29.03.17.
 */

public interface ProjectsModel {
    Observable<ProjectsResponse> getData(ProjectsRequest request);
}
