package org.styleru.styleruapp.view;

import org.styleru.styleruapp.model.dto.VacanciesItem;
import org.styleru.styleruapp.model.dto.support.Request;

import java.util.List;

/**
 * Created by tetawex on 25.04.17.
 */

public interface VacancyView extends View {
    void setVacanciesData(List<Request> data);

    void setPrivileges(boolean canApprove, boolean canRecommend);

    void onApproveVacancy(int id, String name);

    void onRecommendVacancy(int id, String name, boolean status);

    void removeVacancy(int id);

    void tickVacancy(int id);

    void notifyVacancyRecommended(String name, boolean status);

    void notifyVacancyApproved(String name);
}
