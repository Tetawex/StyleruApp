package org.styleru.styleruapp.view;


import org.styleru.styleruapp.model.dto.DepartmentsItem;
import org.styleru.styleruapp.model.dto.ProfileProjectsItem;

import java.util.List;

/**
 * Created by Пользователь on 02.04.2017.
 */

public interface ProfileProjectsView extends View {
    void appendData(List<ProfileProjectsItem> data);
    void setData(List<ProfileProjectsItem> data);
    void onDataUpdated();

}
