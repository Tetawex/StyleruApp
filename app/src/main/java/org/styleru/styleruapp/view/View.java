package org.styleru.styleruapp.view;

/**
 * Created by tetawex on 06.03.17.
 */

public interface View {
    void showError(Throwable throwable);
    void startProgressBar();
    void stopProgressBar();
}
