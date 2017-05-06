package org.styleru.styleruapp.view;

import org.styleru.styleruapp.model.dto.ProfileItem;
import org.styleru.styleruapp.model.dto.ProfileResponse;

import java.util.List;

/**
 * Created by Пользователь on 01.05.2017.
 */


public interface ProfileView extends View {
    void setData(ProfileResponse response);
    void appendData(ProfileResponse response);

}