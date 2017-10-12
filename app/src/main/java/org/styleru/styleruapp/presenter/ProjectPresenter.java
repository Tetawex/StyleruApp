package org.styleru.styleruapp.presenter;

/**
 * Created by tetawex on 30.04.17.
 */

public interface ProjectPresenter extends Presenter {
    void onLoadData(int id);

    void onRequestVacancy(int id, boolean accepted);
}
