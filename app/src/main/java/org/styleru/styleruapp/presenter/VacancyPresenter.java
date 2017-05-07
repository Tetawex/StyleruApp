package org.styleru.styleruapp.presenter;

/**
 * Created by tetawex on 25.04.17.
 */

public interface VacancyPresenter extends Presenter {
    void onLoadVacanciesData(int vacancyId);
    void onApproveVacancy(int id);
    void onRecommendVacancy(int id);
}
