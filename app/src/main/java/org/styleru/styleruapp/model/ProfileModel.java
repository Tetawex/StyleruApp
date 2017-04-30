package org.styleru.styleruapp.model;

import org.styleru.styleruapp.model.dto.ProfileRequest;
import org.styleru.styleruapp.model.dto.ProfileProjectsResponse;

import io.reactivex.Observable;

/**
 * Created by Пользователь on 03.04.2017.
 */

public interface ProfileModel extends Model {
    Observable<ProfileProjectsResponse> getData(ProfileRequest request);
}
