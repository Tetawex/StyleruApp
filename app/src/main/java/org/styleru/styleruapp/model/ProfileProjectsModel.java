package org.styleru.styleruapp.model;

import org.styleru.styleruapp.model.dto.ProfileProjectsRequest;
import org.styleru.styleruapp.model.dto.ProfileProjectsResponse;
import org.styleru.styleruapp.model.dto.ProfileProjectsStateChangeRequest;
import org.styleru.styleruapp.model.dto.ProfileProjectsStateChangeResponse;

import io.reactivex.Observable;

/**
 * Created by Пользователь on 03.04.2017.
 */

public interface ProfileProjectsModel extends Model {
    Observable<ProfileProjectsResponse> getData(ProfileProjectsRequest request);
    Observable<ProfileProjectsStateChangeResponse> getChangedState(ProfileProjectsStateChangeRequest request);
}
