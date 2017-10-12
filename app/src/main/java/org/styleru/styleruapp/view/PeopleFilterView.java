package org.styleru.styleruapp.view;

import org.styleru.styleruapp.model.dto.support.PeopleFilter;
import org.styleru.styleruapp.model.dto.support.ProjectsFilter;
import org.styleru.styleruapp.util.Consumer;

/**
 * Created by tetawex on 03.04.17.
 */

public interface PeopleFilterView extends View {
    void setOnFilterDefinedListener(Consumer<PeopleFilter> listener);

    void setFilterModel();
}
