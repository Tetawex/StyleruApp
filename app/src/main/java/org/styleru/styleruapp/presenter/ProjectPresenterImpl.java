package org.styleru.styleruapp.presenter;

import org.styleru.styleruapp.model.ProjectModel;
import org.styleru.styleruapp.model.ProjectModelImpl;
import org.styleru.styleruapp.view.ProjectView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by tetawex on 30.04.17.
 */

public class ProjectPresenterImpl implements ProjectPresenter {
    private Disposable disposable = Disposables.empty();
    private Disposable secDisposable = Disposables.empty();

    private ProjectModel model;
    private ProjectView view;

    public ProjectPresenterImpl(ProjectView view) {
        this.view = view;
        model = new ProjectModelImpl();
    }

    @Override
    public void onStop() {

    }

    @Override
    public void onLoadData(int id) {
        disposable = model.getProjectData(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response ->
                        {
                            view.stopProgressBar();
                            view.inflateData(response);
                        },
                        throwable ->
                        {
                            view.stopProgressBar();
                            view.showError(throwable);
                        },
                        () -> {
                            if (!disposable.isDisposed()) {
                                disposable.dispose();
                            }
                        });
    }

    @Override
    public void onRequestVacancy(int id, boolean accepted) {
        secDisposable = model.requestVacancy(id).subscribe(
                () -> view.notifyVacancyRequestCompleted(id, !accepted),
                throwable -> view.showError(throwable));
    }
}
