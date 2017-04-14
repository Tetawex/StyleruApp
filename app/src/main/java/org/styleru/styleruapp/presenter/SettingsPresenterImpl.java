package org.styleru.styleruapp.presenter;

import org.styleru.styleruapp.model.SettingsModel;
import org.styleru.styleruapp.model.SettingsModelImpl;
import org.styleru.styleruapp.model.dto.support.Settings;
import org.styleru.styleruapp.view.SettingsView;

import io.reactivex.disposables.Disposable;

/**
 * Created by Tetawex on 14.04.2017.
 */

public class SettingsPresenterImpl implements SettingsPresenter {
    private SettingsModel model;
    private SettingsView view;
    private Disposable disposable;
    private Disposable secondaryDisposable;

    public SettingsPresenterImpl(SettingsView view) {
        this.model = new SettingsModelImpl();
        this.view = view;
    }
    @Override
    public void onSettingsSync(Settings settings) {
        secondaryDisposable =model.setData(settings)
                .subscribe(response ->
                        {
                            view.stopProgressBar();
                            view.notifySyncState(response.getStatus());
                        },
                        throwable ->
                        {
                            view.stopProgressBar();
                            view.notifySyncState(0);
                        },
                        () -> {
                            if(!secondaryDisposable.isDisposed()) {
                                secondaryDisposable.dispose();
                            }
                        });
    }

    @Override
    public void onSettingsModelDownload() {
        view.startProgressBar();
        disposable =model.getData()
                .subscribe(response ->
                        {
                            view.setData(response.getSettingsDataModel());
                        },
                        throwable ->
                        {
                            view.showError(throwable);
                        },
                        () -> {
                            if(!disposable.isDisposed()) {
                                disposable.dispose();
                            }
                            view.stopProgressBar();
                        });
    }
}
