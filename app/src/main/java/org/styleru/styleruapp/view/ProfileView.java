package org.styleru.styleruapp.view;

import org.styleru.styleruapp.model.dto.ProfileItem;

import java.util.List;

/**
 * Created by Пользователь on 01.05.2017.
 */


public interface ProfileView extends View {
    void setData(List<ProfileItem> data);
    void appendData(List<ProfileItem> data);

}