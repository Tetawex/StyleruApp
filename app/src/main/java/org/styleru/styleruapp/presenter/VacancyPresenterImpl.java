package org.styleru.styleruapp.presenter;

import org.styleru.styleruapp.model.VacancyModel;
import org.styleru.styleruapp.model.VacancyModelImpl;
import org.styleru.styleruapp.view.VacancyView;

import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;

/**
 * Created by tetawex on 27.04.17.
 */

public class VacancyPresenterImpl implements VacancyPresenter {
    private VacancyView view;
    private VacancyModel model;

    private Disposable loadVacanciesDataDisposable= Disposables.empty();
    private Disposable approveVacancyDisposable= Disposables.empty();
    private Disposable recommendVacancyDisposable= Disposables.empty();

    public VacancyPresenterImpl(VacancyView view) {
        this.view = view;
        model=new VacancyModelImpl();
    }

    @Override
    public void onLoadVacanciesData(int vacancyId) {
        loadVacanciesDataDisposable=model.getVacancyData(vacancyId)
                .subscribe(response -> {
                    view.setVacanciesData(response.getRequests());
                },throwable -> {

                },()->{
                    if(!loadVacanciesDataDisposable.isDisposed())
                        loadVacanciesDataDisposable.dispose();
                });
    }

    @Override
    public void onApproveVacancy(int id) {
        approveVacancyDisposable=model.approveVacancy(id)
                .subscribe(() -> {
                    view.removeVacancy(id);
                },throwable -> {
                    view.showError(throwable);
                });
    }

    @Override
    public void onRecommendVacancy(int id) {
        recommendVacancyDisposable=model.recommendVacancy(id)
                .subscribe(() -> {
                    view.removeVacancy(id);
                },throwable -> {
                    view.showError(throwable);
                });
    }
    @Override
    public void onStop() {
    }
}
