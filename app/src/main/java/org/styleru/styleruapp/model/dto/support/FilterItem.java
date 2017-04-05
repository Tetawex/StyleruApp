package org.styleru.styleruapp.model.dto.support;

/**
 * Created by tetawex on 04.04.17.
 */

public interface FilterItem extends IdItem{
    @Override
    int getId();

    String getName();
    boolean isChecked();
    void setChecked(boolean checked);

}
