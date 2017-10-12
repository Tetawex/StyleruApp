package org.styleru.styleruapp.presenter;

/**
 * Created by tetawex on 06.03.17.
 */

public interface DepartmentsPresenter extends Presenter {
    void onDataAppend(int offset, int batchSize);

    void onDataUpdate(int batchSize);
}