package org.styleru.styleruapp.model;

import org.styleru.styleruapp.model.dto.ProjectsRequest;
import org.styleru.styleruapp.model.dto.ProjectsResponse;
import org.styleru.styleruapp.model.dto.SignUpRequest;

import io.reactivex.Observable;

/**
 * Created by Tetawex on 31.03.2017.
 */

public interface SignUpModel extends Model{
    Observable<ProjectsResponse> getSignUpResponse(SignUpRequest request);
}
