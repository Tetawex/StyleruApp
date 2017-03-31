package org.styleru.styleruapp.model;

import org.styleru.styleruapp.model.dto.DepartmentsRequest;
import org.styleru.styleruapp.model.dto.DepartmentsResponse;

import io.reactivex.Observable;

/**
 * Created by Tetawex on 31.03.2017.
 */

public interface DepartmentsModel extends Model {
    Observable<DepartmentsResponse> getSignUpResponse(DepartmentsRequest request);
}
