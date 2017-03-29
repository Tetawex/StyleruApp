package org.styleru.styleruapp.presenter;

/**
 * Created by tetawex on 29.03.17.
 */

public interface ProjectsPresenter extends Presenter{
    void onProjectsAppend(int offset, int batchSize,String requestString);
    void onProjectsUpdate(int batchSize,String requestString);
    void onSetFilterMode(int filterMode);
}
