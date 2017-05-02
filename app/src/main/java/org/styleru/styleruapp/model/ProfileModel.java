package org.styleru.styleruapp.model;

import org.styleru.styleruapp.model.dto.ProfileRequest;
import org.styleru.styleruapp.model.dto.ProfileProjectsResponse;
import org.styleru.styleruapp.model.dto.ProfileResponse;

import io.reactivex.Observable;

/**
 * Created by Пользователь on 03.04.2017.
 */

public interface ProfileModel extends Model {
    Observable<ProfileResponse> getData(ProfileRequest request);
}
