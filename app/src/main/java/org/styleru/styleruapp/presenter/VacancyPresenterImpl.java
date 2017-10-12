package org.styleru.styleruapp.presenter;

import android.util.Log;

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

    private Disposable loadVacanciesDataDisposable = Disposables.empty();
    private Disposable approveVacancyDisposable = Disposables.empty();
    private Disposable recommendVacancyDisposable = Disposables.empty();

    public VacancyPresenterImpl(VacancyView view) {
        this.view = view;
        model = new VacancyModelImpl();
    }

    @Override
    public void onLoadVacanciesData(int vacancyId) {
        loadVacanciesDataDisposable = model.getVacancyData(vacancyId)
                .subscribe(response -> {
                    view.setPrivileges(response.isApproveSubmission(), response.isRecommendSubmission());
                    Log.e("crocs", response.isApproveSubmission() + "" + response.isRecommendSubmission());
                    view.setVacanciesData(response.getRequests());
                    view.stopProgressBar();
                }, throwable -> {

                }, () -> {
                    if (!loadVacanciesDataDisposable.isDisposed())
                        loadVacanciesDataDisposable.dispose();
                });
    }

    @Override
    public void onApproveVacancy(int id, String name) {
        approveVacancyDisposable = model.approveVacancy(id)
                .subscribe(() -> {
                    view.removeVacancy(id);
                    view.notifyVacancyApproved(name);
                }, throwable -> {
                    view.showError(throwable);
                });
    }

    @Override
    public void onRecommendVacancy(int id, String name, boolean status) {
        recommendVacancyDisposable = model.recommendVacancy(id)
                .subscribe(() -> {
                    view.tickVacancy(id);
                    view.notifyVacancyRecommended(name, !status);
                }, throwable -> {
                    view.showError(throwable);
                });
    }

    @Override
    public void onStop() {
    }
}
