package org.styleru.styleruapp.model;

import org.styleru.styleruapp.model.dto.ProjectResponse;
import org.styleru.styleruapp.model.dto.ProjectsResponse;

import io.reactivex.Completable;
import io.reactivex.Observable;

/**
 * Created by tetawex on 30.04.17.
 */

public interface ProjectModel extends Model {
    Observable<ProjectResponse> getProjectData(int id);

    Completable requestVacancy(int id);
}
