package org.styleru.styleruapp.view;

import org.styleru.styleruapp.model.dto.VacanciesItem;
import org.styleru.styleruapp.model.dto.support.Request;

import java.util.List;

/**
 * Created by tetawex on 25.04.17.
 */

public interface VacancyView extends View {
    void setVacanciesData(List<Request> data);

    void setPrivilegies(boolean canApprove, boolean canRecommend);

    void onApproveVacancy(int id);
    void onRecommendVacancy(int id);
    void removeVacancy(int id);
}
