package org.styleru.styleruapp.presenter;

import org.styleru.styleruapp.model.DepartmentsModel;
import org.styleru.styleruapp.model.DepartmentsModelImpl;
import org.styleru.styleruapp.model.EventsModel;
import org.styleru.styleruapp.model.EventsModelImpl;
import org.styleru.styleruapp.view.DepartmentsView;
import org.styleru.styleruapp.view.EventsView;

import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;

/**
 * Created by tetawex on 06.03.17.
 */

public class DepartmentsPresenterImpl implements DepartmentsPresenter {
    //TODO: заменить инъекцию через конструктор инъекцией дагером
    private DepartmentsView view;
    private DepartmentsModel model;

    private Disposable disposable = Disposables.empty();

    private int currentId = 0;

    public DepartmentsPresenterImpl(DepartmentsView view) {
        this.view = view;
        //TODO: заменить тестовую модель на настоящую, когда сделают api
        this.model = new DepartmentsModelImpl();
    }

    @Override
    public void onDataAppend(int offset, int batchSize) {
        currentId = offset;
        disposable = model.getData(currentId, batchSize)
                .subscribe(response -> view.appendData(response.getData()),
                        throwable -> view.showError(throwable),
                        () -> {
                            if (!disposable.isDisposed()) {
                                disposable.dispose();
                            }
                        });
        currentId += batchSize;
    }

    @Override
    public void onDataUpdate(int batchSize) {
        currentId = 0;
        if (!disposable.isDisposed()) {
            disposable.dispose();
        }
        disposable = model.getData(currentId, batchSize)
                .subscribe(response ->
                        {
                            view.stopProgressBar();
                            view.setData(response.getData());
                            view.onDataUpdated();
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
        currentId += batchSize;
    }

    @Override
    public void onStop() {

    }
}
