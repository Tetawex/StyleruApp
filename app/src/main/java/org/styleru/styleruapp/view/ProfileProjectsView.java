package org.styleru.styleruapp.view;


import org.styleru.styleruapp.model.dto.SingleProfileItem;

import java.util.List;

/**
 * Created by Пользователь on 02.04.2017.
 */

public interface ProfileProjectsView extends View {
    void appendData(List<SingleProfileItem> data);
    void setData(List<SingleProfileItem> data);
    void changeProfileProjectsState(int id);
    void onDataUpdated();
}
