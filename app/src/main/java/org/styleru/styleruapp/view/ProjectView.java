package org.styleru.styleruapp.view;

import org.styleru.styleruapp.model.dto.ProjectResponse;

/**
 * Created by tetawex on 30.04.17.
 */

public interface ProjectView extends View {
    void inflateData(ProjectResponse response);

    void requestVacancy(int id, boolean accepted);

    void notifyVacancyRequestCompleted(int id, boolean accepted);
}
