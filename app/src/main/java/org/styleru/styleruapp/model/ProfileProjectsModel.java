package org.styleru.styleruapp.model;

import org.styleru.styleruapp.model.dto.ProfileProjectsRequest;
import org.styleru.styleruapp.model.dto.ProfileProjectsResponse;

import io.reactivex.Observable;

/**
 * Created by Пользователь on 03.04.2017.
 */

public interface ProfileProjectsModel extends Model {
    Observable<ProfileProjectsResponse> getData(int offset,int batchSize);
}
