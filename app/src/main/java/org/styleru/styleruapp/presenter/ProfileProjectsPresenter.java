package org.styleru.styleruapp.presenter;

/**
 * Created by Пользователь on 02.04.2017.
 */


public interface ProfileProjectsPresenter extends Presenter {
    void onDataAppend(int offset, int batchSize);

    void onDataUpdate(int batchSize);
}
