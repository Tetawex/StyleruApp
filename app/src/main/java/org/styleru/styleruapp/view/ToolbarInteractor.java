package org.styleru.styleruapp.view;

import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

/**
 * Created by Tetawex on 07.04.2017.
 */

public interface ToolbarInteractor {
    enum Mode {BASIC, SPINNER}

    void setToolbarTitleMode(Mode mode);

    void setToolbarElevationDp(float elevationDp);

    void setToolbarVisible(boolean visible);

    void setToolbarTitle(String title);

    void setToolbarSpinnerAdapter(SpinnerAdapter adapter);

    void setToolbarSpinnerListener(AdapterView.OnItemSelectedListener listener);
}
